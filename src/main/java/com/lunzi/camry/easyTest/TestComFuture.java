package com.lunzi.camry.easyTest;

import com.lunzi.camry.domain.User;

import java.util.function.Supplier;

/**
 * 测试并发
 * Created by lunzi on 2019/2/8 4:26 PM
 */
public class TestComFuture {
    public static void testSupplyAsync(){
        Supplier<User> supplier=User::new;
        User user=supplier.get();
        System.out.println(user);

    }

    public static void main(String[] args) {
        TestComFuture.testSupplyAsync();
    }
}
