package org.geek;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(PersonProperties.class)
@ConditionalOnProperty(prefix = "person", name = "enable", havingValue = "true", matchIfMissing = true)
public class PersonServiceAutoConfiguration {

    @Autowired
    private PersonProperties personProperties;

    @Bean
    public PersonService personService() {
        return new PersonService(personProperties);
    }

}
