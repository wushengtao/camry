package com.lunzi.camry.threadpool;

/**
 * Created by lunzi on 2018/7/20 上午8:59
 */
public class PrintABC implements Runnable{
    private String name;
    private Object pre;
    private Object self;

    public PrintABC(String name,Object pre,Object self){
        this.name=name;
        this.pre=pre;
        this.self=self;
    }

    @Override
    public void run() {
        int count=10;
        while(count>0){
            synchronized (pre){
                synchronized (self){
                    System.out.println(name);
                    count--;
                    self.notify();
                }
                try {
                    pre.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }
    public static void main(String args[]) throws InterruptedException {
        Object a=new Object();
        Object b=new Object();
        Object c=new Object();
        PrintABC pa=new PrintABC("a",c,a);
        PrintABC pb=new PrintABC("b",a,b);
        PrintABC pc=new PrintABC("c",b,c);
        new Thread(pa).start();
        Thread.sleep(100);
        new Thread(pb).start();
        Thread.sleep(100);
        new Thread(pc).start();
        Thread.sleep(100);
    }
}
