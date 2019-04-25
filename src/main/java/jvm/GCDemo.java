package jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * -Xms20M -Xmx20M -Xmn10M -verbose:gc -XX:+PrintGCDetails -XX:+UseSerialGC
 * 年轻代 10M 老年代 10  eden 8M suvivor2M
 * Created by lunzi on 2019/4/23 7:29 PM
 * -Xloggc:/Users/user21/Documents/testgit/gc.log
 */
public class GCDemo {
    public static void main(String[] args) throws InterruptedException {
        //eden 8M
        List<GCDataObject> list = new ArrayList<GCDataObject>();
        for (int i = 0; i < 4; i++) {
            list.add(new GCDataObject(2));
        }

        //表示第二次fullGC，或者MinorGC可以把他回收！
        list = null;

        List<GCDataObject> list2 = new ArrayList<GCDataObject>();
        for (int i = 0; i < 3; i++) {
            list2.add(new GCDataObject(2));
        }
        list2 = null;

    }
}

class GCDataObject {
    byte[] bytes = null;

    public GCDataObject(int i) {
        bytes = new byte[i * 1024 * 1024];
    }
}
