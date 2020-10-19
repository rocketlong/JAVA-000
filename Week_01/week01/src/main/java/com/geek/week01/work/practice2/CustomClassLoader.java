package com.geek.week01.work.practice2;

public class CustomClassLoader extends AbstractClassLoader {

    public CustomClassLoader(String path) {
        super(path);
    }

    @Override
    protected byte[] getClassFileBytes(String path) throws Exception {
        byte[] bytes = super.getClassFileBytes(path);
        byte[] resBytes = new byte[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            resBytes[i] = (byte) (255 - bytes[i]);
        }
        return resBytes;
    }

}
