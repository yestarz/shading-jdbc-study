package com.yyt.shardingjdbcstudy.config04;

import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingValue;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author yangxin
 * @date 2021/9/14
 */
public class OrderShardingAlgorithm implements ComplexKeysShardingAlgorithm<Long> {
    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, ComplexKeysShardingValue<Long> shardingValue) {
        Map<String, Collection<Long>> columnNameAndShardingValuesMap = shardingValue.getColumnNameAndShardingValuesMap();
        List<Long> collection = (List<Long>) columnNameAndShardingValuesMap.get("order_id");
        Long orderId = collection.get(0);
        if (orderId < 10) {
            return Arrays.asList(shardingValue.getLogicTableName());
        }
        return Arrays.asList(shardingValue.getLogicTableName() + "_" + (orderId % 2));
    }

}
