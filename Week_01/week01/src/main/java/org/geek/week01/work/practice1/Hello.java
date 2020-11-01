package org.geek.week01.work.practice1;

public class Hello {

    // javac Week_01/week01/src/main/java/com/geek/week01/work/practice1/Hello.java
    // javap -c Week_01/week01/src/main/java/com/geek/week01/work/practice1/Hello.class
    // javap -c -verbose Week_01/week01/src/main/java/com/geek/week01/work/practice1/Hello.class
    public static void main(String[] args) {
        int a = 1;
        int b = 2;
        int c = a * b;
        int d = b / a;
        int e = a + b;
        int f = b - a;
        double g = a * b;
        for (int i = 0; i < 9; i++) {
            if (a == b) b++;
            else a++;
        }
    }

}
