package com.lunzi.camry.nio;

import com.lunzi.camry.aop.CountRunTime;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.file.Paths;

/**
 * Created by lunzi on 2019/3/24 10:44 AM
 */
@Component
public class OpenFile {
    /**
     * mmap
     * @throws IOException
     */
    public static File file=new File("/Users/user21/Documents/workspace/camry/src/main/resources/json/a.txt");
    public static void mmapRea() throws IOException {
        Resource resource=new ClassPathResource("json\\a.txt");
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

    public static void fileChannerlWrite() throws IOException {
        Resource resource=new ClassPathResource("json\\a.txt");
        FileChannel fileChannel=new FileOutputStream(resource.getFile()).getChannel();
        ByteBuffer byteBuffer=ByteBuffer.wrap("这时一条新的记录".getBytes());
        fileChannel.write(byteBuffer);
        fileChannel.close();

    }

    @CountRunTime
    public  void read(){
        try (FileInputStream is = new FileInputStream(new File("/Users/user21/Documents/workspace/camry/src/main/resources/json/a.txt"))) {
            BufferedInputStream bufferedInputStream=new BufferedInputStream(is);
            //每次读取1024字节
            byte[] bytes=new byte[1024];
            bufferedInputStream.read(bytes);
            bufferedInputStream.close();
            //输出
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @CountRunTime
    public   void  inputStreamWrite() throws Exception {
        OutputStream fileOutputStream=
                new FileOutputStream(file);
        for(int i=0;i<1000000;i++){
            fileOutputStream.write(getStr().getBytes());
        }
    }



    public void bufferWrite() throws Exception {
        FileOutputStream fileOutputStream=new FileOutputStream(file);
        BufferedOutputStream bufferedOutputStream=new BufferedOutputStream(fileOutputStream);
        byte [] bytes=new byte[1024];
        bufferedOutputStream.write(bytes);
        bufferedOutputStream.flush();
    }

    public void channelWrite() throws FileNotFoundException {
        FileChannel fileChannel=new FileInputStream(file).getChannel();
    }

    @CountRunTime
    public void nioRead() throws IOException {
        FileInputStream is =
                new FileInputStream(new File("/Users/user21/Documents/workspace/camry/src/main/resources/json/a.txt"));
        FileChannel fileChannel=is.getChannel();
        ByteBuffer byteBuffer=ByteBuffer.allocate(128);
        fileChannel.read(byteBuffer);
        fileChannel.close();

    }

    @CountRunTime
    public void mmapRead() throws IOException {
        FileInputStream is =
                new FileInputStream(new File("/Users/user21/Documents/workspace/camry/src/main/resources/json/a.txt"));
        FileChannel fileChannel=is.getChannel();
        long size=fileChannel.size();
        MappedByteBuffer byteBuffer=fileChannel.map(FileChannel.MapMode.READ_ONLY,0,size);
        fileChannel.close();

    }
    public static void mmapWrire() throws IOException {
        FileOutputStream out =
                new FileOutputStream(new File("/Users/user21/Documents/workspace/camry/src/main/resources/json/a.txt"));
        FileChannel fileChannel=out.getChannel();
        MappedByteBuffer byteBuffer=fileChannel.map(MapMode.READ_WRITE, 0, fileChannel.size());
        byteBuffer.put(new String("111111").getBytes());

    }

    public static String getStr(){
        StringBuilder stringBuilder=new StringBuilder();
        for(int i=0;i<4096;i++){
            stringBuilder.append("1");
        }
        return stringBuilder.toString();
    }
    public static void main(String[] args) throws Exception {

    }
}
