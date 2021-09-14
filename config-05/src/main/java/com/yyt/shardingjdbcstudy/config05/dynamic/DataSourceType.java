package com.yyt.shardingjdbcstudy.config05.dynamic;

/**
 * @author yangxin
 * @date 2021/9/13
 */
public enum DataSourceType {

    DEFAULT("druidDataSource"),
    SHARDING("shardingDataSource");

    private final String type;

    DataSourceType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
