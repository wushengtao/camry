package com.lunzi.camry.design.observePattern;

/**
 * Created by lunzi on 2019/4/26 11:01 PM
 */
public class ConcreteObser implements Obser{
    @Override
    public void update() {
        System.out.println("receiev update,do action");
    }
}
