package com.lunzi.camry.design.chainPattern;

/**
 * Created by lunzi on 2019/4/2 4:00 PM
 */
public class HandlerChain {
    public static void main(String[] args) {
       ValidateHandler validateHandler=new ValidateHandler();
       validateHandler.setNextHandler(new CoreHandler());
       validateHandler.process();
    }
}
