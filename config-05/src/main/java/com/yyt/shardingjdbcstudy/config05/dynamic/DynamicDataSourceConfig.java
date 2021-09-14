package com.yyt.shardingjdbcstudy.config05.dynamic;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yangxin
 * @date 2021/9/13
 */
@Configuration
public class DynamicDataSourceConfig {

    @Bean
    public DynamicDataSource dynamicDataSource(DruidDataSource druidDataSource, DataSource shardingDataSource){
        DynamicDataSource dynamicDataSource = new DynamicDataSource();

        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put("druidDataSource", druidDataSource);
        targetDataSources.put("shardingDataSource", shardingDataSource);
        dynamicDataSource.setTargetDataSources(targetDataSources);
        dynamicDataSource.setDefaultTargetDataSource(druidDataSource);
        return dynamicDataSource;
    }

    @Bean
    public PlatformTransactionManager platformTransactionManager(DataSource dynamicDataSource) {
        return new DataSourceTransactionManager(dynamicDataSource);
    }

}
