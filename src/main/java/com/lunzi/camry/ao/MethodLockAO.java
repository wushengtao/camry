package com.lunzi.camry.ao;

/**
 * Created by lunzi on 2018/9/12 上午10:54
 */
public interface MethodLockAO {
    /**
     * 获取指定名字的锁
     * @param lockName
     * @return
     */
    boolean getLock(String lockName);

    boolean getLockByName(String lockName);



}
