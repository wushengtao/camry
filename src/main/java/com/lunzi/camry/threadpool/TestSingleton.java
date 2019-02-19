package com.lunzi.camry.threadpool;

/**
 * Created by lunzi on 2018/10/19 上午11:04
 */
public class TestSingleton {
    private TestSingleton(){
        System.out.println("create");
    }
    private static class SingletonHolder {
        private static TestSingleton testSingleton=new TestSingleton();
    }
    public TestSingleton getInstance(){
        return SingletonHolder.testSingleton;
    }
}
