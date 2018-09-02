package com.lunzi.camry.cache.iml;

/**
 * Created by lunzi on 2018/6/17 下午3:02
 */
public class CacheObj<K, V> {
    final K key;
    final V obj;

    long lastAccess;//上次访问时间

    long accessCount;//访问次数

    long ttl;//对象存活时长 0表示永久存活

    public CacheObj(K key, V obj, long ttl) {
        this.key = key;
        this.obj = obj;
        this.ttl = ttl;
        this.lastAccess = System.currentTimeMillis();
    }

    boolean isExpired() {
        return (this.lastAccess + this.ttl < System.currentTimeMillis()) && (this.ttl > 0);
    }

    V get(boolean isUpdateLastAccess) {
        if (isUpdateLastAccess) {
            lastAccess = System.currentTimeMillis();
        }
        accessCount++;
        return obj;
    }

    public K getKey() {
        return this.key;
    }

    public V getValue() {
        return this.obj;
    }
    //toString
}
