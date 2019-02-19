package com.lunzi.camry.function;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.lunzi.camry.domain.QuestionContent;
import com.lunzi.camry.mapper.QuestionContentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.*;

/**
 * Created by lunzi on 2018/9/27 上午10:10
 */
@Component
public class TestFutureImpl implements TestFuture {
    @Autowired
    QuestionContentDao questionContentDao;
    @Override
    public void updateALL() {
//        EntityWrapper<QuestionContent> entityWrapper=new EntityWrapper<>();
//        QuestionContent questionContent=new QuestionContent();
//        questionContent.setStatus(1);
//        entityWrapper.setEntity(questionContent);
//        List<QuestionContent> list=questionContentDao.selectList(entityWrapper);
//        list.forEach(x->x.setStatus(0));
//        list.forEach(x->{questionContentDao.updateById(x);});
        EntityWrapper<QuestionContent> entityWrapper=new EntityWrapper<>();
        QuestionContent questionContent=new QuestionContent();
        entityWrapper.setEntity(questionContent);
        entityWrapper.ge("question_id",1).le("question_id",500);
        List<QuestionContent> list=questionContentDao.selectList(entityWrapper);
        list.forEach(x->x.setStatus(3));
        list.forEach(x->{questionContentDao.updateById(x);});
        System.out.println(list.size());
    }

    @Override
    public void updateBatchAll() {
        ExecutorService es= Executors.newFixedThreadPool(10);
        int index=1;
        while(index<=600){
            SimpleFuture simpleFuture=new SimpleFuture(index,index+500);
            Future<Boolean> flag=es.submit(simpleFuture);
//            try {
//               flag.get();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            }
            index=index+500;
        }
    }
    class SimpleFuture implements Callable<Boolean> {
        private int start;
        private int end;
        SimpleFuture(int start,int end){
            this.start=start;
            this.end=end;
        }
        @Override
        public Boolean call() throws Exception {
            EntityWrapper<QuestionContent> entityWrapper=new EntityWrapper<>();
            QuestionContent questionContent=new QuestionContent();
            entityWrapper.setEntity(questionContent);
            entityWrapper.ge("question_id",start).le("question_id",end);
            List<QuestionContent> list=questionContentDao.selectList(entityWrapper);
            list.forEach(x->x.setStatus(6));
            list.forEach(x->{questionContentDao.updateById(x);});
            return true;
        }
    }
}

