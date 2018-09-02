package com.lunzi.camry.tool.core.lang;

import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

/**
 * 缓存类
 * Created by lunzi on 2018/7/19 上午8:30
 */
public class SimpleCache<K, V> {
    private final Map<K, V> cache = new WeakHashMap<>();//弱引用
    private final ReentrantReadWriteLock cacheLock = new ReentrantReadWriteLock();
    private final ReadLock readLock = cacheLock.readLock();
    private final WriteLock writeLock = cacheLock.writeLock();

    /**
     * 读取缓存
     * @param key
     * @return
     */
    public V get(K key) {
        readLock.lock();//读所
        V value;
        try {
            value = cache.get(key);
        } finally {
            readLock.unlock();//解锁
        }
        return value;
    }

    /**
     * 放入缓存
     * @param key
     * @param value
     * @return
     */
    public V put(K key,V value){
        writeLock.lock();
        try{
            cache.put(key,value);
        }finally {
            writeLock.unlock();
        }
        return value;
    }

    /**
     * 移除缓存
     * @param key
     * @return
     */
    public V remove(K key){
        writeLock.lock();
        try{
            return cache.remove(key);
        }finally {
            writeLock.unlock();
        }
    }

    /**
     * 清除缓存池
     */
    public void clear(){
        writeLock.lock();
        try{
            this.cache.clear();
        }finally {
            writeLock.unlock();
        }
    }
}
