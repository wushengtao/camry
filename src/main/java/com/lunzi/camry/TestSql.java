package com.lunzi.camry;

import org.springframework.util.ResourceUtils;

import java.io.*;

/**
 * Created by lunzi on 2019/1/29 5:23 PM
 */

public class TestSql {
    public static void main(String agrs[]) throws IOException {
        long start = System.currentTimeMillis();
        try {
            File src = ResourceUtils.getFile("classpath:json/fling.com_40M_users.sql");
            File desc = ResourceUtils.getFile("classpath:json/fling");
            FileInputStream fis = new FileInputStream(src);
            FileOutputStream fos = new FileOutputStream(desc);
            byte[] b = new byte[1024 * 1024];
            int len = -1;
            while ((len = fis.read(b)) != -1) {
                fos.write(b, 0, len);
                fos.flush();
            }
            fos.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("共用时：" + (end - start) + "秒");
    }
}
