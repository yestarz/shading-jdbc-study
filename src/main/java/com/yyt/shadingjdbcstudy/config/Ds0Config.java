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
@ConfigurationProperties(prefix = "spring.datasource.ds0")
@Data
public class Ds0Config {

    private String driverClassName;

    private String url;

    private String username;

    private String password;

    protected static final Logger logger = LoggerFactory.getLogger(Ds0Config.class);

    @Bean
    public DruidDataSource druidDataSource0() {
        logger.info("初始化ds0连接池");
        return DataSourceUtil.getDruidDataSource(url, driverClassName, username, password);
    }

}
