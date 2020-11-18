package org.geek.week05.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "org.geek.week05.spring")
public class TestConfiguration {

    @Conditional(WindowsCondition.class)
    @Bean(name = "student04")
    public Student getStudent() {
        return new Student(4, "K04");
    }

}
