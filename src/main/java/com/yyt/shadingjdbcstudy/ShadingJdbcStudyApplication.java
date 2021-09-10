package com.yyt.shadingjdbcstudy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.yyt.shadingjdbcstudy.mapper")
public class ShadingJdbcStudyApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShadingJdbcStudyApplication.class, args);
    }

}
