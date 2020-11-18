package org.geek.demo.controller;

import org.geek.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private PersonService personService;

    @GetMapping("/hello")
    public String hello() {
        return personService.sayHello();
    }

}
