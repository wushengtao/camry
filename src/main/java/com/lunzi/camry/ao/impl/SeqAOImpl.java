package com.lunzi.camry.ao.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.google.common.collect.Lists;
import com.lunzi.camry.ao.SeqAO;
import com.lunzi.camry.domain.Dic;
import com.lunzi.camry.domain.SeqDTO;
import com.lunzi.camry.service.DicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by lunzi on 2018/6/1 下午9:14
 */
@Component
public class SeqAOImpl implements SeqAO {

    @Autowired
    DicService dicService;
    private static Map<String,SeqDTO> map=new ConcurrentHashMap<>();
    private static Long step=3L;//步长
    @Override
    public Long genCurrentTimp() {
        synchronized (this){
            return LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        }
    }

    @Override
    @Transactional
    public void test() {

    }

    @Override
    public Dic getNewest() {
        EntityWrapper<Dic> wrapper=new EntityWrapper<>();
        List<Dic> list= dicService.selectList(wrapper);
        if(CollectionUtils.isEmpty(list)){
            return null;
        }
        return list.get(0);
    }

    @Override
    public Long getNextNumber() {
        //先从缓存里面取值
        SeqDTO seqDTO=map.get("test");
        //如果为空的话 需要重新去生成新的记录，不为空就直接取
        if(seqDTO==null){
            //获取当前数据库里最大的值
            Dic dic=getNewDic();
            if(dic==null){
                //说明数据库还没有数据，就插入新的数据
                SeqDTO seq=insertOne(1L);
                map.put("test",seq);
                return seq.getValue();
            }else {
                SeqDTO seqDTO1= insertOne(dic.getValue()+step);
                map.put("test",seqDTO1);
                return seqDTO1.getValue();
            }

        }
        else {
            //获取seqValue
            Long seqValue=seqDTO.getNextNum();
            if(seqValue==null){
                //本地的缓存值已经用光了，需要重新生成新的
                Dic dic=getNewDic();
                SeqDTO seqDTO1=new SeqDTO();
                seqDTO1.setKeyCode("test");
                seqDTO1.setValue(dic.getValue()+step);
                seqDTO1.setMaxValue(dic.getValue()+step+step-1);
                //插入数据库新的
                insertOne(dic.getValue()+step);
                map.put("test",seqDTO1);
                return seqDTO1.getValue();
            }
            else{
                return seqValue;
            }
        }
    }

    @Override
    public Dic getNewDic() {
        EntityWrapper<Dic> entityWrapper=new EntityWrapper<>();
        entityWrapper.orderBy("id",false);
        List<Dic> dicList=dicService.selectList(entityWrapper);
        if(!CollectionUtils.isEmpty(dicList)){
            return dicList.get(0);
        }else {
            return null;
        }
    }
    @Override
    public SeqDTO insertOne(Long value) {
        Dic dic=new Dic();
        dic.setKeyCode("test");
        dic.setValue(value);
        dicService.insert(dic);
        SeqDTO seqDTO=new SeqDTO();
        seqDTO.setKeyCode("test");
        seqDTO.setValue(value);
        seqDTO.setMaxValue(step-1+value);
        return seqDTO;
    }

}
