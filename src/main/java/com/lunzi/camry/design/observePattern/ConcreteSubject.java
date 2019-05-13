package com.lunzi.camry.design.observePattern;

/**
 * Created by lunzi on 2019/4/26 11:03 PM
 */
public class ConcreteSubject extends Subject{
    public ConcreteSubject() {
        super();
    }

    public void change(){
        System.out.println("state change，notify all obsers");
        update();
    }
}
