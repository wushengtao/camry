package com.lunzi.camry.cache.iml;

import java.util.Iterator;

/**
 * Created by lunzi on 2018/6/17 下午7:47
 */
public class CacheValuesIterator<V> implements Iterator<V> {
    private final CacheObjIterator<?,V> cacheObjIterator;
    CacheValuesIterator(CacheObjIterator<?,V> cacheObjIterator){
        this.cacheObjIterator=cacheObjIterator;
    }
    @Override
    public boolean hasNext() {
        return cacheObjIterator.hasNext();
    }

    @Override
    public V next() {
        return cacheObjIterator.next().getValue();
    }

    @Override
    public void remove() {
        cacheObjIterator.remove();
    }
}
