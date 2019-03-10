package com.lunzi.camry.redis;


/**
 *
 * 注意里面循环的写法
 * Created by lunzi on 2019/3/8 9:07 PM
 */
public class DicLock {
    public void test(){
        int times=5;
        boolean result;
        for(result=getResult(times);!result&&times-->0;result=getResult(times)){
            //锁等待
            try{
                System.out.println("等待"+times);
                Thread.sleep(1000);
            }catch (InterruptedException e){

            }
        }
        System.out.println(result);
    }

    private boolean getResult(int times) {
        times--;
        if(times%2==0){
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        DicLock dicLock=new DicLock();
        dicLock.test();
    }

}
