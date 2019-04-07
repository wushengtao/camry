package com.lunzi.camry.threadpool;

import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.PooledObjectState;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import java.io.PrintWriter;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * Created by lunzi on 2019/4/2 11:18 PM
 */
public class ConnectionPool<T> {
    //泛型对象池
    GenericObjectPoolConfig poolConfig=new GenericObjectPoolConfig();
    GenericObjectPool<T> internalPool=new GenericObjectPool(new PooledObjectFactory() {
        @Override
        public PooledObject makeObject() throws Exception {
            return new PooledObject() {
                @Override
                public Object getObject() {
                    return new Object();
                }

                @Override
                public long getCreateTime() {
                    return 0;
                }

                @Override
                public long getActiveTimeMillis() {
                    return 0;
                }

                @Override
                public long getIdleTimeMillis() {
                    return 0;
                }

                @Override
                public long getLastBorrowTime() {
                    return 0;
                }

                @Override
                public long getLastReturnTime() {
                    return 0;
                }

                @Override
                public long getLastUsedTime() {
                    return 0;
                }

                @Override
                public int compareTo(PooledObject other) {
                    return 0;
                }

                @Override
                public boolean startEvictionTest() {
                    return false;
                }

                @Override
                public boolean endEvictionTest(Deque idleQueue) {
                    return false;
                }

                @Override
                public boolean allocate() {
                    return false;
                }

                @Override
                public boolean deallocate() {
                    return false;
                }

                @Override
                public void invalidate() {

                }

                @Override
                public void setLogAbandoned(boolean logAbandoned) {

                }

                @Override
                public void use() {

                }

                @Override
                public void printStackTrace(PrintWriter writer) {

                }

                @Override
                public PooledObjectState getState() {
                    return null;
                }

                @Override
                public void markAbandoned() {

                }

                @Override
                public void markReturning() {

                }

                @Override
                public int compareTo(Object o) {
                    return 0;
                }
            };
        }

        @Override
        public void destroyObject(PooledObject p) throws Exception {

        }

        @Override
        public boolean validateObject(PooledObject p) {
            return false;
        }

        @Override
        public void activateObject(PooledObject p) throws Exception {

        }

        @Override
        public void passivateObject(PooledObject p) throws Exception {

        }
    },new GenericObjectPoolConfig());

    private T getResource(){
        try {
            return internalPool.borrowObject();
        } catch (Exception e) {
            throw new RuntimeException("error!,e",e);
        }
    }

    public static void main(String[] args) {
        ConnectionPool<Object> connectionPool=new ConnectionPool();
        Set<Integer> set=new HashSet<>();
        for(int i=0;i<100;i++){
            Object obj=connectionPool.getResource();
            set.add(set.hashCode());
        }
        System.out.println(set);
    }
}
