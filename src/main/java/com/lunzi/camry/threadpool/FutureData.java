package com.lunzi.camry.threadpool;

/**
 * Created by lunzi on 2018/10/20 上午11:40
 */
public class FutureData {
    private FutureRealDate  futureRealDate;
    private volatile  boolean isReady;

    public void setFutureRealDate(FutureRealDate futureRealDate) {
        if(isReady){
            return;
        }
        this.futureRealDate=futureRealDate;
        isReady=true;
        notify();
    }
    public String getFutureRealDate(){
        while(isReady){

        }
        return null;
    }
}
