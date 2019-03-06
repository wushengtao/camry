package com.lunzi.camry.design.strategyPattern;

/**
 * Created by lunzi on 2019/3/6 9:15 AM
 */
public class StrategyB implements IStrategy {
    @Override
    public void printPrice() {
        System.out.println("this is strategyB !");
    }
}
