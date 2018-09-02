package com.lunzi.camry.nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Random;
import java.util.RandomAccess;

/**
 * Created by lunzi on 2018/8/7 下午10:39
 */
public class TestBuffer {
    public static void main(String args[]) throws IOException {
        System.out.println(System.getProperty("java.class.path"));
        RandomAccessFile randomAccessFile=new RandomAccessFile("a.txt","rw");
        FileChannel fileChannel=randomAccessFile.getChannel();
        ByteBuffer byteBuffer=ByteBuffer.allocate(512);
        int byteRead=fileChannel.read(byteBuffer);
        while(byteRead!=-1){
            System.out.println("Read " + byteRead);
            byteBuffer.flip();
            while(byteBuffer.hasRemaining()){
                System.out.println((char)byteBuffer.get());
            }
            byteBuffer.clear();
            byteRead=fileChannel.read(byteBuffer);
        }

        randomAccessFile.close();
    }
}
