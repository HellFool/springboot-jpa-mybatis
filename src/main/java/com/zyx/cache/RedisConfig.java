package com.zyx.cache;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;

/**
 * @author : @zyx
 */
@Configuration
public class RedisConfig {
    @Bean
    public Jedis getJedis(){
        return new Jedis("192.168.11.182",7000);
    }
}
