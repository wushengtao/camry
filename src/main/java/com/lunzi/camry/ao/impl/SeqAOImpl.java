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
    private static Long step=10L;//步长
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

        }
        else {
            //获取seqValue
            Long seqValue=seqDTO.getNextNum();
            if(seqValue==null){
                //本地的缓存值已经用光了，需要重新生成新的
            }
            else{
                return seqValue;
            }
        }
        return null;
    }

}
