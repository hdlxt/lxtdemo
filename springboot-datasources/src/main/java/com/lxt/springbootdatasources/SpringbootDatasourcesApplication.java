package com.lxt.springbootdatasources;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@MapperScan("com.lxt.springbootdatasources.mapper")
@SpringBootApplication
public class SpringbootDatasourcesApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootDatasourcesApplication.class, args);
    }

}
