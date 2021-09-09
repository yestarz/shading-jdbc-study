package com.yyt.shadingjdbcstudy.config;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * @author yangxin
 * @date 2021/9/9
 */
public class DataSourceUtil {

    public static DruidDataSource getDruidDataSource(String url, String driverClassName, String username, String password) {
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
