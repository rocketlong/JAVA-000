package org.geek.week05.cglib;

import net.sf.cglib.proxy.Enhancer;

public class ProxyTest {

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Student.class);
        enhancer.setCallback(new MyMethodInterceptor());
        Student student = (Student) enhancer.create();
        student.sayHello();
        student.fly();
        student.speak();
        student.study();
        student.check();
    }

}
