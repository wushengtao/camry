package com.lunzi.camry.design.chainPattern;

/**
 * Created by lunzi on 2019/4/2 3:53 PM
 */
public class ValidateHandler extends BaseHandler{

    @Override
    public void process() {
       //一顿操作
        if(handleSuccess){

        }else {
            nextHandler.process();
        }

    }

}
