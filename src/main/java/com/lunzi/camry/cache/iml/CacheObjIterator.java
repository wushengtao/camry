package com.lunzi.camry.cache.iml;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by lunzi on 2018/6/17 下午5:08
 */
public class CacheObjIterator<K,V> implements Iterator<CacheObj<K,V>> {
    private final Iterator<CacheObj<K,V>> iterator;
    private CacheObj<K,V> nextValue;

    CacheObjIterator(Iterator<CacheObj<K,V>> iterator){
        this.iterator=iterator;
        nextValue();
    }

    /**
     * 下一个值
     */
    private void nextValue() {
        while(iterator.hasNext()){
            nextValue=iterator.next();
            if(nextValue.isExpired()==false){
                return;
            }
        }
        nextValue=null;
    }

    @Override
    public boolean hasNext() {
        return nextValue!=null;
    }

    @Override
    public CacheObj<K, V> next() {
        if(false==hasNext()){
            throw new NoSuchElementException();
        }
        final CacheObj<K,V> cacheObj=nextValue;
        nextValue();
        return cacheObj;

    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Cache values Iterator is not support to modify.");
    }
}
