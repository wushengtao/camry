package com.lunzi.camry.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by lunzi on 2018/9/26 下午5:32
 */
public class TestAnswer {
    public static void main(String args[]) throws IOException {
        StringBuffer buffer=new StringBuffer();
        File file = ResourceUtils.getFile("classpath:json/answer.txt");
//        InputStreamReader inputStreamReader=new InputStreamReader(new FileInputStream(file));
//        char [] c=new char[2048000];
//        int len=inputStreamReader.read(c);
//        System.out.println(len);
//        File file = ResourceUtils.getFile("classpath:json/answer.txt");
//        InputStream inputStream=new FileInputStream(file);

        InputStream is = new FileInputStream(file);
        String line; // 用来保存每行读取的内容
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        line = reader.readLine(); // 读取第一行
        while (line != null) { // 如果 line 为空说明读完了
            buffer.append(line); // 将读到的内容添加到 buffer 中
            buffer.append("\n"); // 添加换行符
            line = reader.readLine(); // 读取下一行
        }
        System.out.println(buffer.toString());
        JSONObject jsonObject=JSONObject.parseObject(buffer.toString());
        //获取题目的json 列表
        List<JSONObject> list= (List) jsonObject.get("items");
        for(JSONObject obj:list){
            System.out.println(obj.get("subject"));
        }
        reader.close();
        is.close();
    }
}
