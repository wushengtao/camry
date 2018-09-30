package com.lunzi.camry.exe.core.lang;

import com.lunzi.camry.exe.core.io.FileUtil;
import com.lunzi.camry.exe.util.ClassUtil;
import com.lunzi.camry.exe.util.StrUtil;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Created by lunzi on 2018/9/14 上午11:19
 */
public class ClassScaner {
    /**
     * 扫描指定包路径下所有包含指定注解的类
     *
     * @param packageName 包路径
     * @param annotationClass 注解类
     * @return 类集合
     */
    public static Set<Class<?>> scanPackageByAnnotation(String packageName,Class<? extends Annotation> annotationClass) throws IOException {
        return scanPackage(packageName,clazz->{return clazz.isAnnotationPresent(annotationClass);});
    }

    public static Set<Class<?>> scanPackage(String packageName, Filter<Class<?>> classFilter) throws IOException {
        if(StringUtils.isEmpty(packageName)){
            packageName="";
        }
        packageName = getWellFormedPackageName(packageName);
        //获取ClassLoader
        final Set<Class<?>> classes=new HashSet<>();
        final Set<String> classPaths=ClassUtil.getClassPath(packageName,true);
        for(String classPath:classPaths){
            fillClass(classPath,packageName,classFilter,classes);
        }

        return null;
    }

    /**
     *
     * @param path
     * @param packageName
     * @param classFilter
     * @param classes
     */
    private static void fillClass(String path, String packageName, Filter<Class<?>> classFilter, Set<Class<?>> classes) {
        int index=path.lastIndexOf(".jar!");
        if(index!=-1){
            //匹配到了
            path=path.substring(0,index+".jar".length());
            //去除前缀
            path=StrUtil.removePrefix(path,FileUtil.PATH_FILE_PRE);
            //是jar 要处理jar
            processJarFile(new File(path),packageName,classFilter,classes);

        }
    }

    /**
     * jar 文件
     * @param file
     * @param packageName
     * @param classFilter
     * @param classes
     */
    private static void processJarFile(File file, String packageName, Filter<Class<?>> classFilter, Set<Class<?>> classes) {
        JarFile jarFile=null;
        Enumeration<JarEntry> entries;
        try{
            jarFile=new JarFile(file);
            entries=jarFile.entries();
            JarEntry jarEntry;
            String entryName;
            while(entries.hasMoreElements()){
                jarEntry=entries.nextElement();
                entryName=jarEntry.getName();
                if(isClass(entryName)){

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean isClass(String fileName) {
        return fileName.endsWith(FileUtil.CLASS_EXT);
    }

    /**
     * 改变 com -> com. 避免在比较的时候把比如 completeTestSuite.class类扫描进去，如果没有"."<br>
     * 那class里面com开头的class类也会被扫描进去,其实名称后面或前面需要一个 ".",来添加包的特征
     *
     * @param packageName
     * @return 格式化后的包名
     */
    private static String getWellFormedPackageName(String packageName) {
        return packageName.lastIndexOf(StrUtil.DOT) != packageName.length() - 1 ? packageName + StrUtil.DOT : packageName;
    }

    private static void fillClassed(String classPath, String packageName, Filter<Class<?>> classFilter, Set<Class<?>> classes) {

    }

    public static void main(String args[]) throws IOException {
        ClassLoader classLoader=ClassUtil.getClassLoader();
//        System.out.println(classLoader.getResource("com/alibaba"));
        Enumeration<URL> enumeration=classLoader.getResources("com");
        while(enumeration.hasMoreElements()){
            String path=enumeration.nextElement().getPath();
            System.out.println(path);
        }
    }
}
