package com.yyt.shadingjdbcstudy.config.keygen;

import com.yyt.shadingjdbcstudy.util.SpringUtil;
import org.apache.shardingsphere.spi.keygen.ShardingKeyGenerator;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * 基于Redis实现的主键自增策略
 *
 * @author yangxin
 * @date 2021/9/9
 */
public class RedisShardingKeyGenerator implements ShardingKeyGenerator {

    private Properties properties;

    @Override
    public Comparable<?> generateKey() {
        String redisKey = this.properties.getProperty("redis_key");

        StringRedisTemplate stringRedisTemplate = SpringUtil.getBean(StringRedisTemplate.class);
        Long id = stringRedisTemplate.opsForValue().increment(redisKey);
        System.out.println(id);
        return id;
    }

    @Override
    public String getType() {
        return "REDIS_AUTO";
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
