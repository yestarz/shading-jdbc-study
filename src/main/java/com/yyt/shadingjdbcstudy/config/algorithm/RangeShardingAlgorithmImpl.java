package com.yyt.shadingjdbcstudy.config.algorithm;

import com.google.common.collect.Range;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author yangxin
 * @date 2021/9/9
 */
public class RangeShardingAlgorithmImpl implements RangeShardingAlgorithm<Long> {

    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, RangeShardingValue<Long> shardingValue) {
        Range<Long> valueRange = shardingValue.getValueRange();
        Long lowerEndpoint = valueRange.lowerEndpoint();
        Long upperEndpoint = valueRange.upperEndpoint();
        Collection<String> result = new ArrayList<>();

        long beginIndex = lowerEndpoint % 3;
        long endIndex = 2;

        for (long i = beginIndex; i <= endIndex; i++) {
            result.add(shardingValue.getLogicTableName() + "_" +  i);
        }

        return result;
    }
}
