package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootApplication
public class DockerDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DockerDemoApplication.class, args);
	}

}
