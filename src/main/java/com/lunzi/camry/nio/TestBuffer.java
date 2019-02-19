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
//        System.out.println(System.getProperty("java.class.path"));
//        RandomAccessFile randomAccessFile=new RandomAccessFile("a.txt","rw");
//        FileChannel fileChannel=randomAccessFile.getChannel();
//        ByteBuffer byteBuffer=ByteBuffer.allocate(512);
//        int byteRead=fileChannel.read(byteBuffer);
//        while(byteRead!=-1){
//            System.out.println("Read " + byteRead);
//            byteBuffer.flip();
//            while(byteBuffer.hasRemaining()){
//                System.out.println((char)byteBuffer.get());
//            }
//            byteBuffer.clear();
//            byteRead=fileChannel.read(byteBuffer);
//        }
//
//        randomAccessFile.close();
        String str="002300781c5b94d9b3f972d408004500" +
                "005b007140007206b3672f6b6446c0a8" +
                "006b0050c905b5f580e144f478f65018" +
                "040186020000c1311aa8a0c5763622ce" +
                "4366e6373fd9b3ecd98ef58f1b1a5fb6" +
                "7582aaa0e6f9e00389a9765eb3b121a2" +
                "8569a0674068730c00";

        str=str.toUpperCase();
        String hexDigital="0123456789ABCDEF";
        char [] hexs=str.toCharArray();
        byte [] bytes=new byte[str.length()/2];
        int n;
        for(int i=0;i<bytes.length;i++){
            n=hexDigital.indexOf(hexs[2*i])*16+hexDigital.indexOf(hexs[2*i+1]);
            bytes[i]=(byte)(n&0xff);
        }
        str=new String(bytes,"UTF-8");
        System.out.println(str);
    }
}
