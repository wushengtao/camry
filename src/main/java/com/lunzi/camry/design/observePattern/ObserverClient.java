package com.lunzi.camry.design.observePattern;

/**
 * Created by lunzi on 2019/4/26 11:05 PM
 */
public class ObserverClient {
    public static void main(String[] args) {
        ConcreteSubject concreteSubject=new ConcreteSubject();
        ConcreteObser c1=new ConcreteObser();
        ConcreteObser c2=new ConcreteObser();
        concreteSubject.attach(c1);
        concreteSubject.attach(c2);
        concreteSubject.change();
    }
}
