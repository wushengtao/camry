package jvm;

/**
 * -XX:+UseSerialGC -Xms200M -Xmx200M -Xmn32m -XX:SurvivorRatio=8 -XX:+PrintGCDetails
 * Created by lunzi on 2019/4/23 11:40 AM
 */
public class FullGC {
    public static void main(String[] args) throws InterruptedException {
        //模拟fullgc场景
        //老年代空间不足
        //按照上面的参数推算:老年代大小: 200 -32m = 168M


        byte[] MAXOBJ = new byte[1024 * 1024 * 100]; // 100M

        byte[] MAXOBJ2 = new byte[1024 * 1024 * 30]; // 60M
        //MAXOBJ = null;

        Thread.sleep(100000);
        byte[] MAXOBJ3 = new byte[1024 * 1024 * 100]; // 60M


    }
}
