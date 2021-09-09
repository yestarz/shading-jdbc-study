package com.yyt.shadingjdbcstudy.config.algorithm;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;

/**
 * @author yangxin
 * @date 2021/9/9
 */
public class PreciseShardingAlgorithmImpl implements PreciseShardingAlgorithm<Long> {
    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<Long> shardingValue) {
        Long order_id = shardingValue.getValue();
        long index = order_id % 3;
        return shardingValue.getLogicTableName() + "_" + index;
    }
}
