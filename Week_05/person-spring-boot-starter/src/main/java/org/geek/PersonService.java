package org.geek;

public class PersonService {

    private PersonProperties personProperties;

    public PersonService(PersonProperties personProperties) {
        this.personProperties = personProperties;
    }

    public String sayHello() {
        return "大家好，我叫: " + personProperties.getName() + ", 今年" + personProperties.getAge() + "岁";
    }

}
