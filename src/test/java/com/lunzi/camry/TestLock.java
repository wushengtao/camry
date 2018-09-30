package com.lunzi.camry;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.lunzi.camry.ao.MethodLockAO;
import com.lunzi.camry.domain.MethodLock;
import com.lunzi.camry.domain.QuestionContent;
import com.lunzi.camry.function.TestFuture;
import com.lunzi.camry.mapper.MethodLockDao;
import com.lunzi.camry.mapper.QuestionContentDao;
import com.lunzi.camry.spring.SpringContextUtil;
import com.oracle.jrockit.jfr.ValueDefinition;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
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

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * Created by lunzi on 2018/9/12 上午11:27
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestLock {
    @Autowired
    private MethodLockAO methodLockAO;
    @Autowired
    private MethodLockDao methodLockDao;
    @Autowired
    private QuestionContentDao questionContentDao;
    @Autowired
    private TestFuture testFuture;

    @Test
    public void test() {
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    String lockName = "test";
                    Boolean flag = methodLockAO.getLock(lockName);
                    System.out.println(flag);
                }
            });
            thread.run();
        }
    }

    @Test
    public void testForUpdate() throws InterruptedException, SQLException {

//        SqlSessionFactory sqlSessionFactory = SpringContextUtil.getBean(SqlSessionFactory.class);
//        SqlSession sqlSession = sqlSessionFactory.openSession(false);
//        //sqlSession.getConnection().setAutoCommit(false);
////            MethodLockDao methodLockDao=sqlSession.getMapper(MethodLockDao.class);
////            MethodLock methodLock=methodLockDao.selectLockForUpdate("test");
//        List<MethodLock> list
//                =sqlSession.selectList("com.lunzi.camry.mapper.MethodLockDao.selectLockForUpdate", "test");
//        Thread.sleep(100000);
        methodLockAO.getLockByName("test");


    }
    @Test
    public void insert(){
        Long start=System.currentTimeMillis();
        testFuture.updateALL();
        Long end=System.currentTimeMillis();
        System.out.println(end-start);
    }
}
