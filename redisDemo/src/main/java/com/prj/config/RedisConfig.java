package com.prj.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @className: com.piesat.config.RedisConfig
 * @description: redis连接的配置类
 * @author: pengrj
 * @create: 2022-09-21 10:45
 */
@Configuration
public class RedisConfig {

    @Value("${spring.redis.host}")
    private String redisHost;

    @Value("${spring.redis.port}")
    private int redisPort;

    /**
     * 获取RedisTemplate，key和value序列化都是String
     *
     * @param databaseIndex 数据库下标
     * @return RedisTemplate
     */
    public RedisTemplate<String, String> getRedisTemplate(Integer databaseIndex) {
        RedisTemplate<String, String> redisTemplate = getRedisConnectionFactory(databaseIndex);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    /**
     * 获取RedisTemplate，key序列化是String，Value序列化是JackSon类型
     *
     * @param databaseIndex 数据库下标
     * @return RedisTemplate
     */
    public RedisTemplate<String, Object> getJacksonRedisTemplate(Integer databaseIndex) {
        RedisTemplate<String, Object> redisTemplate = codeRedisTemplate(databaseIndex);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    /**
     * 是负责建立Factory的连接工厂类
     *
     * @param database 数据库
     * @return RedisTemplate<String, String>
     */
    private RedisTemplate<String, String> getRedisConnectionFactory(int database) {
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
        configuration.setHostName(redisHost);
        configuration.setDatabase(database);
        configuration.setPort(redisPort);
        LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory(configuration);
        lettuceConnectionFactory.afterPropertiesSet();

        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new StringRedisSerializer());
        redisTemplate.setConnectionFactory(lettuceConnectionFactory);
        return redisTemplate;
    }

    /**
     * redisTemplate 序列化使用的jdkSerializeable, 存储二进制字节码, 所以自定义序列化类
     *
     * @param database      数据库地址
     * @return RedisTemplate<String, String>
     */
    private RedisTemplate<String, Object> codeRedisTemplate(int database) {
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
        configuration.setHostName(redisHost);
        configuration.setDatabase(database);
        configuration.setPort(redisPort);
        LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory(configuration);
        lettuceConnectionFactory.afterPropertiesSet();
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        //配置连接工厂
        redisTemplate.setConnectionFactory(lettuceConnectionFactory);

        //指定序列化,用于 序列|反序列 redis的 key|value
        StringRedisSerializer redisSerializer = new StringRedisSerializer();
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);

        ObjectMapper objectMapper = new ObjectMapper();
        //指定序列化的域， filed|get|set|修饰符范围 ，ANY包括所有 private|public
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        //指定序列化输入的类型，必须为非final修饰
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

        redisTemplate.setConnectionFactory(lettuceConnectionFactory);
        //key的序列化方式
        redisTemplate.setKeySerializer(redisSerializer);
        redisTemplate.setHashKeySerializer(redisSerializer);
        //value的序列化方式
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
        return redisTemplate;
    }
}
