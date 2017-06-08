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
import org.springframework.context.annotation.ImportResource;
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
//@ImportResource("classpath:application.xml")
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
        ds.setUrl("jdbc:mysql://172.16.1.181:3306/tbb_account_rd?characterEncoding=utf-8");
        ds.setUsername("tbb_account_rd");
        ds.setPassword("tbb_account_rd");

        ds.setTestOnBorrow(true);
        ds.setTestWhileIdle(true);

        return ds;
    }

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        JedisConnectionFactory factory = new JedisConnectionFactory();

        factory.setHostName("172.16.1.22");
        factory.setPort(6384);
        factory.setPassword("tbbkfTEST");
        factory.setDatabase(9);
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
