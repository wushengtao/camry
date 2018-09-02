package com.lunzi.camry.threadpool;

import java.time.LocalDateTime;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * Created by lunzi on 2018/8/16 上午8:44
 */
public class TestDelay {
    public static void main(String args[]) {

    }
}

class Message implements Delayed {
    private int id;
    private String msg;
    private long runtime;

    public Message(int id, String msg, Long delayTime) {
        this.id = id;
        this.msg = msg;
        this.runtime = System.currentTimeMillis() + delayTime;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(runtime - System.currentTimeMillis(), TimeUnit.SECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        if (o == this) {
            return 0;
        }
        if (o instanceof Message) {
            Message message = (Message) o;
            if (this.id > message.id) {
                return 1;
            } else if (this.id < message.id) {
                return -1;
            } else {
                return 0;
            }
        }
        throw new RuntimeException();
    }
}