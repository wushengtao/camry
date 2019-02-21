package com.lunzi.camry.enums;

import java.util.Objects;

/**
 * 通用枚举
 * Created by lunzi on 2019/2/20 9:05 PM
 */
public interface CommonEnum {
    Integer getCode();

    //接口现在可以实现静态的方法
    static <T extends CommonEnum> T getByCode(Integer code,Class<T> emClass){
        //反射获取
        T [] enums=emClass.getEnumConstants();
        for(T en:enums){
            if(Objects.equals(en.getCode(),code)){
                return en;
            }
        }
        return null;
    }
}
