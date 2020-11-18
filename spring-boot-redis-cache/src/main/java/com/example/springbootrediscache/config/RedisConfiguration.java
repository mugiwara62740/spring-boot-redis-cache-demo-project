package com.example.springbootrediscache.config;

import io.lettuce.core.ReadFrom;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStaticMasterReplicaConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "spring.redis")
public class RedisConfiguration {

    private RedisInstance master;
    private List<RedisInstance> slaves;
    private String password;

    @Bean
    RedisTemplate<String, String> redisTemplate() {
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        //To use increment method in repository
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        redisTemplate.setHashValueSerializer(stringRedisSerializer);
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        redisTemplate.setDefaultSerializer(stringRedisSerializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        LettuceClientConfiguration clientConfig = LettuceClientConfiguration.builder()
                .readFrom(ReadFrom.REPLICA_PREFERRED)
                .build();
        RedisStaticMasterReplicaConfiguration staticMasterReplicaConfiguration = new RedisStaticMasterReplicaConfiguration(this.getMaster().getHost(), this.getMaster().getPort());
        if(password != null){
            staticMasterReplicaConfiguration.setPassword(RedisPassword.of(password));
        }
        this.getSlaves().forEach(slave -> staticMasterReplicaConfiguration.addNode(slave.getHost(), slave.getPort()));
        return new LettuceConnectionFactory(staticMasterReplicaConfiguration, clientConfig);
    }

    RedisInstance getMaster() {
        return master;
    }

    void setMaster(RedisInstance master) {
        this.master = master;
    }

    List<RedisInstance> getSlaves() {
        return slaves;
    }

    void setSlaves(List<RedisInstance> slaves) {
        this.slaves = slaves;
    }

    String getPassword() {
        return password;
    }

    void setPassword(String password) {
        this.password = password;
    }

    private static class RedisInstance {

        private String host;
        private int port;

        String getHost() {
            return host;
        }

        void setHost(String host) {
            this.host = host;
        }

        int getPort() {
            return port;
        }

        void setPort(int port) {
            this.port = port;
        }
    }

}