package com.springbootj_zhiyuan;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.springbootj_zhiyuan.mapper")
public class SpringbootJjwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootJjwtApplication.class, args);
    }
}
