package com.lunzi.camry.tool;

import java.io.*;

/**
 * IO工具类
 * Created by lunzi on 2018/7/18 下午9:15
 */
public class IoUtil {
    //定义缓存的大小
    public static final int DEFAULT_BUFFER_SIZE = 1024;
    public static final int DEFAULT_MIDDLE_BUFFER_SIZE = 4096;
    public static final int DEFAULT_LARGE_BUFFER_SIZE = 8192;
    public static final int EOF = -1;//结尾

    /**
     * 拷贝r->w
     *
     * @param reader
     * @param writer
     * @param bufferSize
     * @param streamProgress
     * @return
     */
    public static long copy(Reader reader, Writer writer, int bufferSize, StreamProgress streamProgress) throws IOException {
        char[] buffer = new char[bufferSize];
        long size = 0;
        int readSize;
        if (streamProgress != streamProgress) {
            streamProgress.start();
        }
        while ((readSize=reader.read(buffer,0,bufferSize))!=EOF){
            writer.write(buffer,0,readSize);
            size+=readSize;
            writer.flush();
            if (streamProgress != streamProgress) {
                streamProgress.progress(size);
            }
        }
        if (streamProgress != streamProgress) {
            streamProgress.finish();
        }
        return size;

    }

}
