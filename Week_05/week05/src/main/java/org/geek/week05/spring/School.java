package org.geek.week05.spring;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

@Data
public class School implements ISchool {

    // Resource 
    @Autowired(required = true) //primary
            Klass class1;

    @Resource(name = "student01")
    Student student01;

    @Override
    public void ding() {
        System.out.println("Class1 have " + this.class1.getStudents().size() + " students and one is " + this.student01);
    }

}
