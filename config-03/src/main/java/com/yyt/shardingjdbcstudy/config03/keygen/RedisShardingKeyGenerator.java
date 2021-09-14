package com.yyt.shardingjdbcstudy.config03.keygen;

import com.yyt.shardingjdbcstudy.config03.util.SpringUtil;
import org.apache.shardingsphere.spi.keygen.ShardingKeyGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Properties;

/**
 * 基于Redis实现的主键自增策略
 *
 * @author yangxin
 * @date 2021/9/9
 */
public class RedisShardingKeyGenerator implements ShardingKeyGenerator {

    private Properties properties;

    private StringRedisTemplate stringRedisTemplate;

    protected static final Logger logger = LoggerFactory.getLogger(RedisShardingKeyGenerator.class);

    @Override
    public Comparable<?> generateKey() {
        this.init();
        String redisKey = this.properties.getProperty("redis_key");
        Long id = stringRedisTemplate.opsForValue().increment(redisKey);
        logger.info("generate id by RedisShardingKeyGenerator.currentId is :{}",id);
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

    private void init() {
        if (stringRedisTemplate == null) {
            stringRedisTemplate = SpringUtil.getBean(StringRedisTemplate.class);
        }
    }

}
