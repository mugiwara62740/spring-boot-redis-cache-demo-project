package com.example.springbootrediscache;


import io.lettuce.core.ReadFrom;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

@SpringBootApplication
public class SpringBootRedisCacheApplication {
	@Bean
	RedisTemplate<String, String> redisTemplate(RedisConnectionFactory connectionFactory) {
		RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
		//To use increment method in repository
		redisTemplate.setHashKeySerializer(new StringRedisSerializer(StandardCharsets.UTF_8));
		redisTemplate.setHashValueSerializer(new StringRedisSerializer(StandardCharsets.UTF_8));
		redisTemplate.setConnectionFactory(redisConnectionFactory());
		redisTemplate.afterPropertiesSet();
		return redisTemplate;
	}

	@Bean
	public RedisConnectionFactory redisConnectionFactory()  {
		RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration("172.21.101.40", 12002);
		redisStandaloneConfiguration.setPassword(RedisPassword.of("52wJmtmEVuAsL"));
		JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(redisStandaloneConfiguration, JedisClientConfiguration.builder()
				.connectTimeout(Duration.ofMinutes(1))
				.readTimeout(Duration.ofMinutes(1))
				.usePooling()
				.build());
		jedisConnectionFactory.getPoolConfig().setMaxTotal(200);
		jedisConnectionFactory.getPoolConfig().setMaxIdle(200);
		return jedisConnectionFactory;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRedisCacheApplication.class, args);
	}

}
