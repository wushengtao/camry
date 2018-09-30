package com.lunzi.camry.reflect;

import com.lunzi.camry.exe.core.io.FileUtil;
import com.lunzi.camry.exe.util.StrUtil;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by lunzi on 2018/9/20 上午8:55
 */
public class TestClassLoader {
    public void processJar() throws IOException {
        ClassLoader classLoader=this.getClass().getClassLoader();
        Enumeration<URL> enumeration=classLoader.getResources("com/alibaba/druid");
        final Set<String> pathSet=new HashSet<>();
        if(enumeration.hasMoreElements()){
            URL url=enumeration.nextElement();
            String path=url.getPath();
            pathSet.add(path);
        }
        final Set<Class<?>> classSet=new HashSet<>();
        for(String path:pathSet){
            int index=path.lastIndexOf(FileUtil.JAR_PATH_EXT);
            path = path.substring(0, index + FileUtil.JAR_FILE_EXT.length()); // 截取jar路径
            path = StrUtil.removePrefix(path, FileUtil.PATH_FILE_PRE); // 去掉文件前缀
        }

    }
    public static void main(String args[]) throws IOException {
        TestClassLoader testClassLoader=new TestClassLoader();
        testClassLoader.processJar();
    }
}
