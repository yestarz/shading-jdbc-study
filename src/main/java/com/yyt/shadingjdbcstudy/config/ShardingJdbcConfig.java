package com.yyt.shadingjdbcstudy.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.yyt.shadingjdbcstudy.config.algorithm.PreciseShardingAlgorithmImpl;
import com.yyt.shadingjdbcstudy.config.algorithm.RangeShardingAlgorithmImpl;
import org.apache.shardingsphere.api.config.sharding.KeyGeneratorConfiguration;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.ComplexShardingStrategyConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.InlineShardingStrategyConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.StandardShardingStrategyConfiguration;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author yangxin
 * @date 2021/9/9
 */
@Configuration
public class ShardingJdbcConfig {

    protected static final Logger logger = LoggerFactory.getLogger(ShardingJdbcConfig.class);

    @Bean
    public DataSource shardingDataSource(DruidDataSource druidDataSource0, DruidDataSource druidDataSource1) {
        // 配置真实数据源
        Map<String, DataSource> dataSourceMap = new HashMap<>();

        // 配置第一个数据源
        dataSourceMap.put("ds0", druidDataSource0);
        dataSourceMap.put("ds1", druidDataSource1);

        // 配置Order表规则
        TableRuleConfiguration orderTableRuleConfig = new TableRuleConfiguration("t_order",
                "ds${0..1}.t_order_${0..2}");
        // orderTableRuleConfig.setKeyGeneratorConfig(new KeyGeneratorConfiguration("MY", "order_id"));

        KeyGeneratorConfiguration keyGeneratorConfiguration = new KeyGeneratorConfiguration("REDIS_AUTO", "order_id");
        keyGeneratorConfiguration.getProperties().setProperty("redis_key", "order_id_gen");
        orderTableRuleConfig.setKeyGeneratorConfig(keyGeneratorConfiguration);
        // 分表策略
        /*orderTableRuleConfig.setTableShardingStrategyConfig((new InlineShardingStrategyConfiguration("order_id",
                "t_order_${order_id % 2}")));*/

        orderTableRuleConfig.setTableShardingStrategyConfig(new StandardShardingStrategyConfiguration("order_id",
                new PreciseShardingAlgorithmImpl(), new RangeShardingAlgorithmImpl()));

        // 分库策略
        orderTableRuleConfig.setDatabaseShardingStrategyConfig(new InlineShardingStrategyConfiguration("user_id",
                "ds${user_id % 2}"));


        // 配置分片规则
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        shardingRuleConfig.getTableRuleConfigs().add(orderTableRuleConfig);

        Properties properties = new Properties();
        properties.put("sql.show", "true");
        try {
            DataSource dataSource = ShardingDataSourceFactory.createDataSource(dataSourceMap, shardingRuleConfig, properties);
            return dataSource;
        } catch (SQLException throwables) {
            logger.error(throwables.getMessage(), throwables);
            throw new RuntimeException(throwables.getCause());
        }
    }

}
