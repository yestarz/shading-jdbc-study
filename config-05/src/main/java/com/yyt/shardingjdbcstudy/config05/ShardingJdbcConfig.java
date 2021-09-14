package com.yyt.shardingjdbcstudy.config05;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.shardingsphere.api.config.sharding.KeyGeneratorConfiguration;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.InlineShardingStrategyConfiguration;
import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author yangxin
 * @date 2021/9/14
 */
@Configuration
public class ShardingJdbcConfig {

    @Bean
    public DataSource shardingDataSource(DruidDataSource druidDataSource) {
        // 配置真实数据源
        Map<String, DataSource> dataSourceMap = new HashMap<>();

        // 配置第一个数据源
        dataSourceMap.put("ds0", druidDataSource);

        // 配置Order表规则
        TableRuleConfiguration orderTableRuleConfig = new TableRuleConfiguration("t_order",
                "ds0.t_order_${0..1}");


        KeyGeneratorConfiguration keyGeneratorConfiguration = new KeyGeneratorConfiguration("SNOWFLAKE", "order_id");
        //keyGeneratorConfiguration.getProperties().setProperty("redis_key", "order_id_gen");
        orderTableRuleConfig.setKeyGeneratorConfig(keyGeneratorConfiguration);
        // 分表策略
        orderTableRuleConfig.setTableShardingStrategyConfig((new InlineShardingStrategyConfiguration("order_id",
                "t_order_${order_id % 2}")));

        // 配置分片规则
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        shardingRuleConfig.getTableRuleConfigs().add(orderTableRuleConfig);

        Properties properties = new Properties();
        properties.put("sql.show", "true");
        try {
            DataSource dataSource = ShardingDataSourceFactory.createDataSource(dataSourceMap, shardingRuleConfig, properties);
            return dataSource;
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables.getCause());
        }
    }

}
