package com.lunzi.camry.rpc;

import ch.qos.logback.classic.spi.STEUtil;
import com.lunzi.camry.domain.Student;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;

/**
 * Created by lunzi on 2018/9/5 下午10:31
 */
public class RpcClient {
    public static int port=7777;
    public static String address="127.0.0.1";
    public static Student student=new Student();
    public static void main(String args[]) throws Exception {
        Socket socket=new Socket(address,port);
        student.setName("wst");
        ObjectOutputStream objectOutputStream=new ObjectOutputStream(socket.getOutputStream());
        objectOutputStream.writeObject(student);
        socket.shutdownOutput();
        objectOutputStream.close();
    }
}
