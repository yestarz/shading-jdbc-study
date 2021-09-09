package com.yyt.shadingjdbcstudy.config.keygen;

import org.apache.shardingsphere.spi.keygen.ShardingKeyGenerator;

import java.util.Properties;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 使用AtomicLong自增实现主键生成
 *
 * @author yangxin
 * @date 2021/9/9
 */
public class MyShardingKeyGenerator implements ShardingKeyGenerator {

    private AtomicLong atomicLong = new AtomicLong(0);

    private Properties properties = new Properties();

    @Override
    public Comparable<?> generateKey() {
        return atomicLong.incrementAndGet();
    }

    @Override
    public String getType() {
        return "MY";
    }

    @Override
    public Properties getProperties() {
        return this.properties;
    }

    @Override
    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}
