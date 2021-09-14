package com.yyt.shardingjdbcstudy.config02;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yangxin
 * @date 2021/9/14
 */
@Configuration
@ConfigurationProperties(prefix = "spring.datasource.ds1")
@Data
public class DataSource02Config {

    private String driverClassName;

    private String url;

    private String username;

    private String password;

    protected static final Logger logger = LoggerFactory.getLogger(DataSource02Config.class);

    @Bean
    public DruidDataSource druidDataSource2() {
        logger.info("初始化druidDataSource2连接池");
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(url);
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setInitialSize(5);
        dataSource.setMaxActive(10);
        dataSource.setMaxWait(6000);
        dataSource.setMinIdle(5);
        return dataSource;
    }

}
