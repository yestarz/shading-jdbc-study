package com.yyt.shardingjdbcstudy.config04;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * @author yangxin
 * @date 2021/9/14
 */
@Configuration
public class MybatisPlusConfig {

    @Bean
    public MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean(DataSource shardingDataSource) {
        MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        mybatisSqlSessionFactoryBean.setDataSource(shardingDataSource);
        mybatisSqlSessionFactoryBean.setMapperLocations(getResources("classpath*:/mapper/**/*.xml"));

        // 分页设置
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor(DbType.MYSQL);
        paginationInnerInterceptor.setMaxLimit(-1L);
        interceptor.addInnerInterceptor(paginationInnerInterceptor);

        mybatisSqlSessionFactoryBean.setPlugins(interceptor);
        return mybatisSqlSessionFactoryBean;
    }

    private Resource[] getResources(String location) {
        ResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();
        try {
            return resourceResolver.getResources(location);
        } catch (IOException var3) {
            return new Resource[0];
        }
    }

}
