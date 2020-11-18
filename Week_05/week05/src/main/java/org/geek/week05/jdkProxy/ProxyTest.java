package org.geek.week05.jdkProxy;

import java.lang.reflect.Proxy;

public class ProxyTest {

    public static void main(String[] args) {
        JDKDynamicProxy jdkDynamicProxy = new JDKDynamicProxy(new SubjectImpl());
        Subject subject = (Subject) Proxy.newProxyInstance(Subject.class.getClassLoader(), new Class[]{Subject.class}, jdkDynamicProxy);
        subject.sayHello();
    }

}
