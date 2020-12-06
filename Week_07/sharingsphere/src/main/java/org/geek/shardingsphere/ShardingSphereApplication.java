package org.geek.shardingsphere;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@MapperScan("org.geek.shardingsphere.mapper")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class ShardingSphereApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShardingSphereApplication.class, args);
    }

}
