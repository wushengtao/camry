package com.lunzi.camry.bean;

import lombok.Data;

/**
 * Created by lunzi on 2019/2/18 11:30 AM
 */
@Data
public class BizResult<T> {
    private T data;
    private String message;
    private Integer code;

    public BizResult(T data, String message, Integer code) {
        this.data = data;
        this.message = message;
        this.code = code;
    }

    public BizResult(){

    }

    public static <T> BizResult<T> create(Integer code, String message){
        BizResult bizResult=new BizResult();
        bizResult.setCode(code);
        bizResult.setMessage(message);
        return bizResult;
    }
}
