package com.lunzi.camry.ssh;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 执行ssh
 * Created by lunzi on 2018/9/22 上午10:38
 */
public class TestSSH {
    public static void main(String args[]) throws IOException, InterruptedException {
        String path="/Users/user21/Documents/shell/test.sh";
        Process ps=Runtime.getRuntime().exec(path);
        ps.waitFor();
        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(ps.getInputStream()));
        StringBuilder stringBuilder=new StringBuilder();
        String line;
        while((line=bufferedReader.readLine())!=null){
            stringBuilder.append(line);
            stringBuilder.append("\n");
        }
        System.out.println(stringBuilder.toString());

    }
}
