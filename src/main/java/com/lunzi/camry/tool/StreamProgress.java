package com.lunzi.camry.tool;

/**
 * stream 进度条
 * Created by lunzi on 2018/7/18 下午9:20
 */
public interface StreamProgress {

    public void start();

    public void progress(long progressSize);

    public void finish();
}
