package com.lunzi.camry.easyTest;

import java.io.FilterInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * 测试一些加密和编码的的方式
 * Created by lunzi on 2018/11/15 2:17 PM
 */
public class TestPwd {
    private static String str="这是测试用的密文";
    private static String url="http://localhost:8080/test?userName=测试";
    public static void main(String[] args) throws UnsupportedEncodingException, ExecutionException, InterruptedException {
        //base64
        //testBase64();
        //Url
        FutureTask<Integer> futureTask=new FutureTask<>(()->{
            try{
                int i=testUrl();
            }catch (Exception e){
                System.out.println("123");
                return 0;
            }
            return 0;
        });
        ExecutorService es=Executors.newFixedThreadPool(1);
        es.execute(futureTask);
        futureTask.get();

    }
    public static void testBase64() throws UnsupportedEncodingException {
        final Base64.Encoder encoder=Base64.getEncoder();
        final Base64.Decoder decoder=Base64.getDecoder();
        final String encoderText=encoder.encodeToString(str.getBytes("UTF-8"));
        final byte[] decoderText=decoder.decode(encoderText);
        System.out.println("Base64编码为"+encoderText);
        System.out.println("Base64解码为"+new String(decoderText));
    }
    public static Integer testUrl(){
        int i=1/0;
        return 1;
    }
}
