package com.lunzi.camry;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.google.common.collect.Lists;
import com.lunzi.camry.ao.ExeAO;
import com.lunzi.camry.domain.ZhUser;
import com.lunzi.camry.mapper.ZhUserDao;
import com.lunzi.camry.service.ZhUserService;
import com.lunzi.camry.spring.SpringContextUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by lunzi on 2018/10/14 下午2:25
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestMysql {
    @Autowired
    private ZhUserDao zhUserDao;
    @Autowired
    private ZhUserService zhUserService;


    @Test
    public void test() throws InterruptedException {
        long start = System.currentTimeMillis();
        ZhUser zhUser = new ZhUser();
        int lastId=zhUserService.getlastId();
        zhUser.setUpdatedTime(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
        EntityWrapper<ZhUser> wrapper = new EntityWrapper<>();
        wrapper.between("id", 1, 100000);
        zhUserService.update(zhUser, wrapper);
        long end = System.currentTimeMillis();
        System.out.println("finish:" + (end - start));
    }

    @Test
    public void mulTest() {
        long start = System.currentTimeMillis();
        ExecutorService executorService = new ThreadPoolExecutor(50, 50, 6000, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(2000), (r, executor) -> {
            try {
                executor.getQueue().put(r);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        //批量更新
        int segmentMaxSize = 500;
        int maxId = 0;//测试最大id=10000
        int lastId=zhUserService.getlastId();
        List<Future<Integer>> futures = Lists.newArrayList();
        while (maxId <= lastId) {
            int finalMaxId = maxId;
            Callable<Integer> task = () -> {
                ZhUser zhUser = new ZhUser();
                zhUser.setUpdatedTime(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
                EntityWrapper<ZhUser> wrapper = new EntityWrapper<>();
                wrapper.between("id", finalMaxId, (finalMaxId + 500));
                Integer affectNum = zhUserDao.update(zhUser, wrapper);
                return affectNum;
            };
            Future<Integer> future = executorService.submit(task);
            futures.add(future);
            maxId += segmentMaxSize;
        }
        futures.forEach(future-> {
            try {
                System.out.println(future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
        long end = System.currentTimeMillis();
        System.out.println("finish:" + (end - start));
    }

    /**
     * 2222516
     */
    @Test
    public void oneAdd(){
        long start = System.currentTimeMillis();
        ZhUser zhUser = new ZhUser();
        int lastId=zhUserService.getlastId();
        zhUser.setUpdatedTime(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
        EntityWrapper<ZhUser> wrapper = new EntityWrapper<>();
        wrapper.between("id", 1, lastId);
        List<ZhUser> zhUserList=zhUserService.selectList(wrapper);
        for(ZhUser user:zhUserList){
            user.setUserToken(user.getUserToken()+"-testMysql");
        }
        long end = System.currentTimeMillis();
        System.out.println("finish:" + (end - start));
    }
    @Test
    public void handCommmit(){
        //1.获取事务控制管理器
        DataSourceTransactionManager transactionManager = SpringContextUtil.getBean(
                DataSourceTransactionManager.class);
        //2.获取事务定义
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        //3.设置事务隔离级别，开启新事务
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        //4.获得事务状态
        TransactionStatus status = transactionManager.getTransaction(def);
        //业务逻辑
        long start = System.currentTimeMillis();
        ZhUser zhUser = new ZhUser();
        //int lastId=zhUserService.getlastId();
        zhUser.setUpdatedTime(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
        EntityWrapper<ZhUser> wrapper = new EntityWrapper<>();
        wrapper.between("id", 1, 1111258);
        List<ZhUser> zhUserList=zhUserService.selectList(wrapper);
        for(ZhUser user:zhUserList){
            user.setUserToken(user.getUserToken()+"-testMysql2");
            zhUserDao.insert(user);
        }
        long end = System.currentTimeMillis();
        transactionManager.commit(status);
        System.out.println("finish:" + (end - start));
    }

    //3333627
    @Test
    public void testMapperbatchInsert(){
        long start = System.currentTimeMillis();
        //每5000条批量插入一下
        int i=0;
        while(i<=1111258){
            EntityWrapper<ZhUser> wrapper = new EntityWrapper<>();
            wrapper.between("id", i, (i+9999));
            List<ZhUser> zhUserList=zhUserService.selectList(wrapper);
            zhUserList.forEach(user -> user.setUserToken(user.getUserToken()+"-mysql5"));
            zhUserDao.batchInsert(zhUserList);
            i+=10000;
        }
        long end = System.currentTimeMillis();
        System.out.println("finish:" + (end - start));
    }


}
