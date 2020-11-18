package org.geek.week05.cglib;

public class Student extends Father implements Person {

    @Override
    public void speak() {
        System.out.println("i am a student, i can speak...");
    }

    @Override
    public void fly() {
        System.out.println("i am a student, i can fly...");
    }

    public void study() {
        System.out.println("i can study...");
    }

    public final void check() {
        System.out.println("i can check...");
    }

}
