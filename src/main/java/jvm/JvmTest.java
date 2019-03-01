package jvm;


import com.google.common.collect.Lists;
import com.lunzi.camry.domain.Student;

import java.util.List;

/**
 * Created by lunzi on 2019/2/28 9:53 AM
 */
public class JvmTest {
    //测试堆溢出
    //-Xmx200m -Xms200m -XX:+HeapDumpOnOutOfMemoryError
    public static void testOOM() {
        List<Student> list= Lists.newArrayList();
        while(true){
            list.add(new Student());
        }
    }

    public static void main(String[] args) {
        JvmTest.testOOM();
    }
}
