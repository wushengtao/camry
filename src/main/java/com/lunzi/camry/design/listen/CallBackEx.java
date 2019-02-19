package com.lunzi.camry.design.listen;

/**
 * Created by lunzi on 2019/2/14 10:15 PM
 */
public class CallBackEx {
    public void send(Integer num,ICallBack callBack){
        System.out.println("调用send方法");
        callBack.onSuccess();
    }

    public static void main(String[] args) {
        CallBackEx callBackEx=new CallBackEx();
        callBackEx.send(1,()->{
            System.out.println("调用成功");
        });
    }
}
