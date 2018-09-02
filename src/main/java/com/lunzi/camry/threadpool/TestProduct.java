package com.lunzi.camry.threadpool;

/**
 * Created by lunzi on 2018/7/26 下午9:18
 */
public class TestProduct {
    public static void main(String args[]) {
        Middle middle = new Middle();
        Product product = new Product(middle);
        Consume consume = new Consume(middle);
        Thread t1 = new Thread(product);
        Thread t2 = new Thread(consume);
        t2.start();
        t1.start();

    }

}

class Product implements Runnable {
    private Middle middle;

    Product(Middle middle) {
        this.middle = middle;
        System.out.println(middle);
    }

    @Override
    public void run() {
        synchronized (middle) {
            while(true){
                if (middle.num < 5) {
                    middle.num++;
                    System.out.println("生产：" + middle.num);
                    try {
                        Thread.sleep(100);
                        middle.notify();
                        middle.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("满了");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    try {
                        middle.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }

    }
}

class Consume implements Runnable {
    private Middle middle;

    Consume(Middle middle) {
        this.middle = middle;
        System.out.println(middle);
    }

    @Override
    public void run() {
        synchronized (middle) {
            while(true){
                if (middle.num < 0) {
                    System.out.println("没了");
                    try {
                        Thread.sleep(100);
                        middle.wait();
                        System.out.println("123");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    middle.num--;
                    System.out.println("消费：" + middle.num);
                    try {
                        Thread.sleep(100);
                        middle.notify();
                        middle.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }

        }
    }
}

class Middle {
    int num = 3;
}

