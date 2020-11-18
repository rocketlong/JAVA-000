package org.geek.week05.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringDemo {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Student student01 = (Student) context.getBean("student01");
        System.out.println(student01);
        Student student02 = (Student) context.getBean("student02");
        System.out.println(student02);
        Klass klass = (Klass) context.getBean("class1");
        System.out.println(klass.students);

        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(TestConfiguration.class);
        Student student04 = (Student) annotationConfigApplicationContext.getBean("student04");
        System.out.println(student04);
    }

}
