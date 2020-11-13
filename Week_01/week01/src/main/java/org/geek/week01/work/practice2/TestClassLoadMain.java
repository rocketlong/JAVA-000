package org.geek.week01.work.practice2;

import java.lang.reflect.Method;

public class TestClassLoadMain {

    public static void main(String[] args) {
        try {
            Class<?> clazz = new CustomClassLoader("Week_01/week01/src/main/java/org/geek/week01/work/practice2/Hello.xlass")
                    .findClass("Hello"); // 无包名
            Object obj = clazz.newInstance();
            Method hello = clazz.getDeclaredMethod("hello");
            hello.invoke(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
