package com.lunzi.camry.cache;

import com.lunzi.camry.cache.iml.CacheObj;

import java.util.Iterator;

/**
 * Created by lunzi on 2018/6/17 下午2:55
 */
public interface Cache<K,V> extends Iterable{
    int capacity();

    long timeout();

    void put(K key,V object);

    void put(K key,V object,long timeout);

    V get(K key);

    V get(K key,boolean isUpdateLastAccess);

    @Override
    Iterator<V> iterator();

    //返回包含键值的迭代器
    Iterator<CacheObj<K,V>> cacheObjIterator();

    int prune();

    boolean isFull();

    void remove(K key);

    void clear();

    int size();

    boolean isEmpty();

    boolean containsKey(K key);
}
