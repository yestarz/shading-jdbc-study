package com.yyt.shadingjdbcstudy.config;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yangxin
 * @date 2021/9/9
 */
@Configuration
@ConfigurationProperties(prefix = "spring.datasource.ds1")
@Data
public class Ds1Config {

    private String driverClassName;

    private String url;

    private String username;

    private String password;

    protected static final Logger logger = LoggerFactory.getLogger(Ds1Config.class);

    @Bean
    public DruidDataSource druidDataSource1(){
        logger.info("初始化ds1连接池");
        return DataSourceUtil.getDruidDataSource(url, driverClassName, username, password);
    }



}
