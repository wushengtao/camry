package com.lunzi.camry.enums;

/**
 * Created by lunzi on 2019/2/20 9:38 PM
 */
public interface Food {
    enum Fruit implements Food{
        APPLE;
    }
    enum Drink implements Food{
        WATER;
    }
    Food food=Fruit.APPLE;
}
