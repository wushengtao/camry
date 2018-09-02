package com.lunzi.camry.cache;

import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

/**
 *
 * Created by lunzi on 2018/6/10 上午9:49
 */
public class EasyCache<K,V> {
    /**
     * 池
     */
    private final Map<K,V> cache=new WeakHashMap<>();
    private final ReentrantReadWriteLock reentrantReadWriteLock=new ReentrantReadWriteLock();
    private final ReadLock readLock=reentrantReadWriteLock.readLock();
    private final WriteLock writeLock=reentrantReadWriteLock.writeLock();

    public V putVal(K key,V value){
        writeLock.lock();
        try{
            cache.put(key,value);
        }finally {
            writeLock.unlock();
        }
        return value;
    }

    public static void main(String args[]){
        Map map=new HashMap();

    }

}
