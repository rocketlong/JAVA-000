package com.geek.week01.example.caseload;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

public class AbstractClassLoader extends ClassLoader {

    protected String path;

    public AbstractClassLoader(String path) {
        /*
          ClassLoader 有两个构造函数
          一个无参构造：会有一个默认的委托父类，通常是用于启动应用程序的类加载器，加载同一份class文件，并不会产生不同的类
          一个需要传入委托的父类ClassLoader：如果传null，加载同一份class文件，会产生不同的类，如该例子
         */
        super(null);
        this.path = path;
    }

    @Override
    protected Class<?> findClass(String name) {
        try {
            byte[] data = getClassFileBytes(new File(path));
            return defineClass(name, data, 0, data.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private byte[] getClassFileBytes(File file) throws Exception {
        try (FileInputStream fis = new FileInputStream(file);
             ByteArrayOutputStream bos = new ByteArrayOutputStream();
             WritableByteChannel outC = Channels.newChannel(bos)) {
            FileChannel channel = fis.getChannel();
            ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
            while (channel.read(buffer) > 0) {
                buffer.flip();
                outC.write(buffer);
                buffer.clear();
            }
            return bos.toByteArray();
        }
    }

}
