package com.lunzi.camry.cache.iml;

import com.lunzi.camry.cache.Cache;
import com.lunzi.camry.collection.CopiedIterator;

import javax.security.auth.kerberos.KerberosKey;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by lunzi on 2018/6/17 下午3:15
 */
public abstract class AbstractCache<K, V> implements Cache<K, V> {
    protected Map<K, CacheObj<K, V>> cacheMap;

    private final ReentrantReadWriteLock cacheLock = new ReentrantReadWriteLock();
    private final ReentrantReadWriteLock.ReadLock readLock = cacheLock.readLock();
    private final ReentrantReadWriteLock.WriteLock writeLock = cacheLock.writeLock();

    protected int capacity;
    protected long timeout;
    protected boolean existCustomTimeOut;

    protected int hitCount;//命中数
    protected int missCount;//丢失数

    @Override
    public void put(K key, V object) {
        put(key,object,timeout);
    }

    @Override
    public void put(K key, V object, long timeout) {
        writeLock.lock();
        try{
            CacheObj<K,V> co=new CacheObj<>(key,object,timeout);
            if(timeout==0){
                existCustomTimeOut=true;
            }
            if(isFull()){
                pruneCache();
            }
        }finally {
            writeLock.unlock();
        }
    }

    @Override
    public boolean containsKey(K key) {
        readLock.lock();
        try{
            final CacheObj<K,V> co=cacheMap.get(key);
            if(co==null){
                return false;
            }
            if(co.isExpired()){
                //读锁不能升级成写锁
                removeWithoutLock(key);
                missCount++;
                return false;
            }
            return true;
        }finally {
            readLock.unlock();
        }
    }

    public int getHitCount(){
        return hitCount;
    }

    public int getMissCount() {
        return missCount;
    }

    protected  void removeWithoutLock(K key){
        CacheObj<K,V> co=cacheMap.remove(key);
        if(null!=co){
            onRemove(co.key,co.obj);
        }
    }

    @Override
    public V get(K key) {
        return get(key,true);
    }

    @Override
    public V get(K key, boolean isUpdateLastAccess) {
       readLock.lock();
       try{
           final CacheObj<K,V> co=cacheMap.get(key);
           if(co==null){
               missCount++;
               return null;
           }
           if(co.isExpired()){
               removeWithoutLock(key);
               missCount++;
               return null;
           }
           hitCount++;
           return co.get(isUpdateLastAccess);
       }finally {
           readLock.unlock();
       }
    }

    @Override
    public Iterator<V> iterator() {
        CacheObjIterator<K,V> cacheObjIterator= (CacheObjIterator<K, V>) this.cacheObjIterator();
        return  new CacheValuesIterator<V>(cacheObjIterator);
    }

    /**
     * 复制 避免高并发
     * @return
     */
    @Override
    public Iterator<CacheObj<K, V>> cacheObjIterator() {
       CopiedIterator<CacheObj<K,V>> copiedIterator;
       readLock.lock();
       try{
           copiedIterator=CopiedIterator.copyOf(this.cacheMap.values().iterator());
       }finally {
           readLock.unlock();
       }
       return new CacheObjIterator<>(copiedIterator);
    }
    protected abstract int pruneCache();

    @Override
    public final int prune() {
        writeLock.lock();
        try{
            return pruneCache();
        }finally {
            writeLock.unlock();
        }
    }

    /**
     * 对象移除回调方法 默认没有动作
     * @param key
     * @param obj
     */
    protected void onRemove(K key, V obj){

    }

    @Override
    public int capacity() {
        return capacity;
    }

    @Override
    public long timeout() {
        return timeout;
    }

    protected boolean isPruneExpiredActive(){
        return (timeout!=0)||existCustomTimeOut;
    }

    @Override
    public void remove(K key) {
        writeLock.lock();
        CacheObj<K,V> co;
        try{
            co=cacheMap.remove(key);
        }finally {
            writeLock.unlock();
        }
        if(null!=co){
            onRemove(co.key,co.obj);
        }
    }

    @Override
    public void clear() {
        writeLock.lock();
        try{
            cacheMap.clear();
        }finally {
            writeLock.unlock();
        }
    }

    @Override
    public int size() {
        return cacheMap.size();
    }

    @Override
    public boolean isEmpty() {
        return cacheMap.isEmpty();
    }

    @Override
    public String toString() {
        return this.cacheMap.toString();
    }
}
