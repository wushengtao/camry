package com.lunzi.camry.design.chainPattern;

import lombok.Getter;
import lombok.Setter;

/**
 * 抽象处理者
 * Created by lunzi on 2019/4/2 3:42 PM
 */
public abstract class BaseHandler {
    /**
     * 下一个处理者
     */
    @Getter
    @Setter
    public BaseHandler nextHandler;

    public boolean handleSuccess=false;

    public abstract void process();


}
