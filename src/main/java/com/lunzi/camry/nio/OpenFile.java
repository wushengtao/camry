package com.lunzi.camry.nio;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.ResourceUtils;

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
        Resource resource=new ClassPathResource("json\\answer.txt");
        FileInputStream file=new FileInputStream(resource.getFile());
        FileChannel fileChannel=file.getChannel();
        long size=fileChannel.size();
        MappedByteBuffer byteBuffer=fileChannel.map(FileChannel.MapMode.READ_ONLY,0,size);
        int i=0;
        byte [] bs=new byte[13];
        while(byteBuffer.hasRemaining()&&i<13){
            //读取的是字节
            byte b=byteBuffer.get();
            bs[i]=b;
            i++;
        }
        String str=new String(bs,0,13,"utf-8");
        System.out.println(str);
    }

    public static void main(String[] args) throws IOException {
        OpenFile file=new OpenFile();
        file.mmap();
    }
}
