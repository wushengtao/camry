package com.lunzi.camry.ao;

import com.lunzi.camry.domain.Dic;
import com.lunzi.camry.domain.SeqDTO;

/**
 * Created by lunzi on 2018/6/1 下午9:12
 */
public interface SeqAO {
    Long genCurrentTimp();
    void test();
    Dic getNewest();
    Long getNextNumber();
    Dic getNewDic();
    SeqDTO insertOne(Long value);


}
