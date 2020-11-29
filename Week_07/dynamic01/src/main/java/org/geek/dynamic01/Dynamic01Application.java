package org.geek.dynamic01;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@MapperScan("org.geek.dynamic01.mapper")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class Dynamic01Application {

    public static void main(String[] args) {
        SpringApplication.run(Dynamic01Application.class, args);
    }

}
