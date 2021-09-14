package com.yyt.shardingjdbcstudy.facade;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author yangxin
 * @date 2021/9/14
 */
@MapperScan("com.yyt.shardingjdbcstudy.dao")
@SpringBootApplication(scanBasePackages = "com.yyt.shardingjdbcstudy")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
