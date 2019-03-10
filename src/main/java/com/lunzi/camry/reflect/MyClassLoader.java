package com.lunzi.camry.reflect;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

/**
 * 自定义类加载器
 * Created by lunzi on 2019/3/8 9:00 AM
 */
public class MyClassLoader extends ClassLoader {
    public MyClassLoader() {
        super();
    }

    public MyClassLoader(ClassLoader parent) {
        super(parent);
    }

    /**
     * 查找指定的class
     *
     * @param name
     * @return
     * @throws ClassNotFoundException
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        File file = getClassFile(name);
        try {
            byte[] bytes = getClassBytes(file);
            Class<?> c = this.defineClass(name, bytes, 0, bytes.length);
            return c;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.findClass(name);
    }

    private File getClassFile(String name) {
        File file = new File("/Users/user21/Documents/workspace/camry/target/classes/com/lunzi/camry/reflect/Reflect.class");
        return file;
    }

    /**
     * 获取文件的二进制数组
     *
     * @param file
     * @return
     */
    private byte[] getClassBytes(File file) throws Exception {
        FileInputStream fis = new FileInputStream(file);
        FileChannel fc = fis.getChannel();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        WritableByteChannel wbc = Channels.newChannel(baos);
        ByteBuffer by = ByteBuffer.allocate(1024);

        while (true) {
            int i = fc.read(by);
            if (i == 0 || i == -1)
                break;
            by.flip();
            wbc.write(by);
            by.clear();
        }

        fis.close();

        return baos.toByteArray();


    }

    /**
     * Class.getResource(String path)
     * path不以’/'开头时，默认是从此类所在的包下取资源；
     * path  以’/'开头时，则是从ClassPath根下获取；
     * <p>
     * Class.getClassLoader().getResource(String path)
     * path不能以’/'开头时；
     * path是从ClassPath根下获取；
     */
    public void testPath() {

    }

    public static void main(String[] args) throws Exception {
        //MyClassLoader mcl = new MyClassLoader();
        MyClassLoader mcl = new MyClassLoader(ClassLoader.getSystemClassLoader().getParent());
        Class<?> clazz = Class.forName("com.lunzi.camry.reflect.Reflect",true,mcl);
        Object obj = clazz.newInstance();
        System.out.println(obj);
        System.out.println(obj.getClass().getClassLoader());
    }
}
