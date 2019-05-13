package io;

import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * Created by lunzi on 2019/5/4 8:11 PM
 */
public class TestRandomAccess {
    private static String path = TestRandomAccess.class.getClassLoader().getResource("json/a.txt").getPath();

    public static void main(String[] args) throws IOException {
//        System.out.println(path);
//        RandomAccessFile randomAccessFile = new RandomAccessFile(path, "rw");
//        long filepoint = randomAccessFile.getFilePointer();
//        randomAccessFile.seek(1);
//        //开始读文件
//        System.out.println((char) randomAccessFile.read());
//        System.out.println(filepoint);
         testFileMap();
    }

    public static void testFileMap() throws IOException {
        FileChannel fileChannel = FileChannel.open(Paths.get(path), new StandardOpenOption[]{StandardOpenOption.READ});
        long size = fileChannel.size();
        byte [] bs=new byte[1024];
        MappedByteBuffer buffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, size);
        buffer.get(bs,0,(int)size);
        String str=new String(bs,"utf-8");
        System.out.println(str);

    }
}
