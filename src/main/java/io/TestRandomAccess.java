package io;

import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lunzi on 2019/5/4 8:11 PM
 */
public class TestRandomAccess {
    private static String path = TestRandomAccess.class.getClassLoader().getResource("json/a.txt").getPath();

    public static void main(String[] args) {
            test("/Users/user21/Pictures/pap.er/6qVkUPawED0.jpg");
        System.out.println(fileList.size());
    }

    public static List<String> fileList = new ArrayList<>();

    public static void test(String path) {
        File file = new File(path);
        //遍历该目录所有文件和文件夹对象
        File[] files = file.listFiles();
        if (files == null || files.length == 0) {
            return;
        }
        for (int i = 0; i < files.length; i++) {

            if (files[i].isDirectory()) {
                test(files[i].toString());       //递归操作，逐一遍历各文件夹内的文件
            } else {
                if (!files[i].isDirectory()) {
                    fileList.add(files[i].getAbsolutePath());
                }

            }
        }
    }
}
