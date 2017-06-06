package com.ihanyong.springboot.exampler.more;/**
 * Created by hanyong on 2017/6/5.
 */

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * @author hanyong
 * @Date 2017/6/5
 */
@SpringBootApplication
@EnableCaching
public class Application extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Application.class);
    }


    @Bean
    public DataSource dataSource() {
        DataSource ds = new DataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://hostname:port/db_name?characterEncoding=utf-8");
        ds.setUsername("db_user_name");
        ds.setPassword("db_password");

        ds.setTestOnBorrow(true);
        ds.setTestWhileIdle(true);

        return ds;
    }

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        JedisConnectionFactory factory = new JedisConnectionFactory();

        factory.setHostName("host_name");
        factory.setPort(6739);
        factory.setPassword("password");
        factory.setDatabase(1);
        factory.setUsePool(true);
        return factory;

    }

    @Bean("keySerializer")
    public RedisSerializer redisKeySerializer() {
        RedisSerializer serializer = new GenericToStringSerializer(Object.class);
        return serializer;
    }
    @Bean("valueSerializer")
    public RedisSerializer redisValueSerializer() {
        GenericJackson2JsonRedisSerializer serializer = new GenericJackson2JsonRedisSerializer();
        return serializer;
    }

    @Bean
    public RedisTemplate redisTemplate() {
        RedisTemplate template = new RedisTemplate();

        template.setConnectionFactory(jedisConnectionFactory());
        template.setKeySerializer(redisKeySerializer());
        template.setHashKeySerializer(redisKeySerializer());
        template.setValueSerializer(redisValueSerializer());
        template.setHashValueSerializer(redisValueSerializer());

        return template;
    }

}
