package com.lunzi.camry.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;

/**
 * Created by lunzi on 2019/3/24 10:44 AM
 */
public class OpenFile {
    public void mmap() throws IOException {
        FileInputStream file=new FileInputStream(new File("classpath:json/answer.txt"));
        FileChannel fileChannel=file.getChannel();
        long size=fileChannel.size();
        MappedByteBuffer byteBuffer=fileChannel.map(FileChannel.MapMode.READ_ONLY,0,size);
        int i=0;
        while(byteBuffer.hasRemaining()&&i<10){
            byte b=byteBuffer.get(i);
            System.out.println(b);
            i++;
        }
    }

    public static void main(String[] args) throws IOException {
        OpenFile file=new OpenFile();
        file.mmap();
    }
}
