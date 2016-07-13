package de.htwberlin.mae.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfiguration {
    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        return new org.apache.tomcat.jdbc.pool.DataSource();
    }   
    
    
    @Bean
    public JedisConnectionFactory redisConnectionFactory() {
      JedisConnectionFactory redisConnectionFactory = new JedisConnectionFactory();

      // Defaults
      redisConnectionFactory.setHostName("127.0.0.1");
      redisConnectionFactory.setPort(6379);
      return redisConnectionFactory;
    }
 	
    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory cf) {
      RedisTemplate<String, String> redisTemplate = new RedisTemplate<String, String>();
      redisTemplate.setKeySerializer(new StringRedisSerializer());
      redisTemplate.setValueSerializer(new StringRedisSerializer());
      redisTemplate.setConnectionFactory(cf);
      return redisTemplate;
    }
}