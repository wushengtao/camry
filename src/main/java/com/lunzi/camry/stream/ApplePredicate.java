package com.lunzi.camry.stream;

import lombok.Data;

import java.util.List;

/**
 * Created by lunzi on 2018/7/12 上午8:49
 */
public interface ApplePredicate {
    boolean test(Apple apple);
}
class AppleWeightPredicate implements ApplePredicate{

    @Override
    public boolean test(Apple apple) {
        return apple.getWeight()>10;
    }
}
class AppleColorPredicate implements  ApplePredicate{

    @Override
    public boolean test(Apple apple) {
        return apple.getColor().equals("green");
    }
}

@Data
class Apple {
    private int weight;
    private String color;
}

