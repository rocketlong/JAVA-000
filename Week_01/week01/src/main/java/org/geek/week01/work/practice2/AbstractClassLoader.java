package org.geek.week01.work.practice2;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

public class AbstractClassLoader extends ClassLoader {

    private String path;

    public AbstractClassLoader(String path) {
//        super();
        this.path = path;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            byte[] data = getClassFileBytes(path);
            return defineClass(name, data, 0, data.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    protected byte[] getClassFileBytes(String path) throws Exception {
        try (FileInputStream fis = new FileInputStream(new File(path));
             ByteArrayOutputStream bos = new ByteArrayOutputStream();
             WritableByteChannel wtc = Channels.newChannel(bos)) {
            FileChannel channel = fis.getChannel();
            ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
            while (channel.read(buffer) > 0) {
                buffer.flip();
                wtc.write(buffer);
                buffer.clear();
            }
            return bos.toByteArray();
        }
    }

}
