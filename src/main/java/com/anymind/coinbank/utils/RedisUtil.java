package com.anymind.coinbank.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisUtil {

    @Value("${redisIp:redis}")
    private String redisIp;

    @Value("${redisPort:6379}")
    private int redisPort;

    public RedisConnectionFactory initRedisConnFactory() {
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration(redisIp, redisPort);
//        configuration.setPassword("123456");
        JedisConnectionFactory connectionFactory = new JedisConnectionFactory(configuration);
        return connectionFactory;
    }
    @Bean
    public RedisTemplate getRedisTemplate(){
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(initRedisConnFactory());
        return redisTemplate;
    }
}
