package com.lunzi.camry.collection;

import com.lunzi.camry.cache.iml.CacheObj;

import javax.swing.undo.UndoableEditSupport;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by lunzi on 2018/6/17 下午4:23
 */
public class CopiedIterator<E> implements Iterator {

    private List<E> eleList=new LinkedList<>();
    private Iterator<E> listIterator;

    public static <V> CopiedIterator<V> copyOf(Iterator<V> iterator){
        return new CopiedIterator<>(iterator);
    }
    //构造
    public CopiedIterator(Iterator<E> iterator){
        while(iterator.hasNext()){
            eleList.add(iterator.next());
        }
        this.listIterator=eleList.iterator();
    }
    @Override
    public boolean hasNext() {
        return this.listIterator.hasNext();
    }

    @Override
    public E next() {
        return this.listIterator.next();
    }

    @Override
    public void remove() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("This is a read-only iterator");
    }
}
