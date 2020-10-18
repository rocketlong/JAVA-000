package com.geek.week01.example;

import com.geek.week01.work.practice2.CustomClassLoader;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * @author limingjian
 */
public class ClassLoadMain {

    public static void main(String[] args) throws Exception {
        String path = "Week_01/week01/src/main/java/com/geek/week01/example/Foo.class";
        ClassLoader1 loader1 = new ClassLoader1(path);
        ClassLoader2 loader2 = new ClassLoader2(path);
        String className = "com.geek.week01.example.Foo";
        try {
            Class<?> foo1 = loader1.loadClass(className);
            Class<?> foo2 = loader2.loadClass(className);
            System.out.println(foo1 == foo2);
            System.out.println(foo1.getClassLoader().getClass().getName());
            System.out.println(foo2.getClassLoader().getClass().getName());
            System.out.println(foo1.toString());
            System.out.println(foo2.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Class.forName 默认会加载并初始化类（初始化是指静态变量赋值和静态代码块的执行，注：类中常量会在类加载阶段完成）
//        Class<?> aClass = Class.forName("com.geek.week01.example.Foo");

        // newInstance 是实例对象，会执行构造函数
//        Class<?> clazz = new ClassLoader1("Week_01/week01/src/main/java/com/geek/week01/example/Foo.class")
//                .findClass("com.geek.week01.example.Foo");
//        Constructor<?> constructor = clazz.getConstructor(String.class);
//        Object obj = constructor.newInstance("lyl");
//        Method hello = clazz.getDeclaredMethod("hello");
//        hello.invoke(obj);
    }

}
