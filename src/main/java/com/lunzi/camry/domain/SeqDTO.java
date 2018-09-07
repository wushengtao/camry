package com.lunzi.camry.domain;

import lombok.Data;

/**
 * Created by lunzi on 2018/9/2 下午5:36
 */
@Data
public class SeqDTO {
    private Long value;
    private String keyCode;
    private Long maxValue;//可以获取到的最大值

    public Long getNextNum(){
        if(value<maxValue){
            return value++;
        }else{
            return null;
        }
    }

}
