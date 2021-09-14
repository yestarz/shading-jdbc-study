package com.yyt.shardingjdbcstudy.config04.keygen;

import org.apache.shardingsphere.spi.keygen.ShardingKeyGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    protected static final Logger logger = LoggerFactory.getLogger(MyShardingKeyGenerator.class);

    @Override
    public Comparable<?> generateKey() {
        long id = atomicLong.incrementAndGet();
        logger.info("generate id by my KeyGenerator.currentId is :{}",id);
        return id;
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
