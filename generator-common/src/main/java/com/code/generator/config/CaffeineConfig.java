package com.code.generator.config;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * CaffeineConfig
 *
 * @author wuhongbin
 * @ClassName CaffeineConfig
 * @date 2024年04月01日
 * @version: 1.0.0
 */
@Configuration
public class CaffeineConfig {

    /**
     * 创建一个有过期时间的缓存 过期时间5分钟
     * @return
     */
    @Bean
    public Cache caffeineExpireCache(){
        return Caffeine.newBuilder()
                .expireAfterWrite(5, TimeUnit.MINUTES)
                //初始容量为100
                .initialCapacity(100)
                //最大容量为200
                .maximumSize(200)
                .build();
    }

}
