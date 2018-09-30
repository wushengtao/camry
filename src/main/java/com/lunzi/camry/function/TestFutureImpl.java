package com.lunzi.camry.function;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.lunzi.camry.domain.QuestionContent;
import com.lunzi.camry.mapper.QuestionContentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * Created by lunzi on 2018/9/27 上午10:10
 */
@Component
public class TestFutureImpl implements TestFuture {
    @Autowired
    QuestionContentDao questionContentDao;
    @Override
    public void updateALL() {
        EntityWrapper<QuestionContent> entityWrapper=new EntityWrapper<>();
        QuestionContent questionContent=new QuestionContent();
        questionContent.setStatus(1);
        entityWrapper.setEntity(questionContent);
        List<QuestionContent> list=questionContentDao.selectList(entityWrapper);
        list.forEach(x->x.setStatus(0));
        list.forEach(x->{questionContentDao.updateById(x);});
        System.out.println(list.size());
    }

    @Override
    public void updateBatchAll() {
        int index=1;
        while(index<=60000){
            
        }
    }
}
class SimpleFuture implements Callable {
    @Override
    public Object call() throws Exception {
        return null;
    }
}
