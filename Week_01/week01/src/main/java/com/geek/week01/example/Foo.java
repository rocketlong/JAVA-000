package com.geek.week01.example;

public class Foo {

    static {
        System.out.println("执行静态代码块");
    }

    private String name;

    public Foo(String name) {
        this.name = name;
        System.out.println("执行构造方法");
    }

    public void hello() {
        System.out.println("hello " + name);
    }

}
