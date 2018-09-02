package com.lunzi.camry.ao.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.lunzi.camry.ao.SeqAO;
import com.lunzi.camry.domain.Dic;
import com.lunzi.camry.service.DicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * Created by lunzi on 2018/6/1 下午9:14
 */
@Component
public class SeqAOImpl implements SeqAO {
    @Autowired
    DicService dicService;
    @Override
    public Long genCurrentTimp() {
        synchronized (this){
            return LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        }
    }

    @Override
    @Transactional
    public void test() {
//        EntityWrapper<Dic> wrapper = new EntityWrapper<>();
//        wrapper.orderBy("value", false);
//        Dic dic = dicService.selectByPrimaryKeyForUpdate("a").get(0);
//        dic.setValue(dic.getValue() + 1);
//        dicService.insert(dic);
    }

}
