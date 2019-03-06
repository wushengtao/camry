package com.lunzi.camry.design.strategyPattern;

/**
 * Created by lunzi on 2019/3/6 9:10 AM
 */
public class StrategyPattern {
    private IStrategy strategy;

    StrategyPattern(IStrategy strategy){
        this.strategy=strategy;
    }

    public void printPrice(){
        strategy.printPrice();
    }

    public static void main(String[] args) {
        StrategyPattern strategyPatterA=new StrategyPattern(new StrategyA());
        strategyPatterA.printPrice();
        StrategyPattern strategyPatternB=new StrategyPattern(new StrategyB());
        strategyPatternB.printPrice();
    }
}
