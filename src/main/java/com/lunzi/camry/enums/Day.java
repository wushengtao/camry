package com.lunzi.camry.enums;

import lombok.Getter;

/**
 * Created by lunzi on 2019/2/20 8:53 PM
 */

public enum Day implements CommonEnum{
    MONDAY(0,"星期一"){
    },
    TUESDAY(1,"星期二"){
    };
    @Getter
    private String desc;
    @Getter
    private Integer code;

    Day(Integer code,String desc) {
        this.desc = desc;
        this.code = code;
    }

    public static void main(String[] args) {

    }

}
