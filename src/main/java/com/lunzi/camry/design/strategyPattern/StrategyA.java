package com.lunzi.camry.design.strategyPattern;

/**
 * Created by lunzi on 2019/3/6 9:14 AM
 */
public class StrategyA implements IStrategy {
    @Override
    public void printPrice() {
        System.out.println("this is strategyA !");
    }
}
