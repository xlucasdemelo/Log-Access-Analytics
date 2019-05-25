package com.lucas.LAA.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.lucas.LAA.queue.MessagePublisher;
import com.lucas.LAA.queue.MessagePublisherImplementation;
import com.lucas.LAA.queue.MessageSubscriber;

@Configuration
@ComponentScan("com.lucas")
@EnableRedisRepositories(basePackages = "com.lucas.LAA.domain.repository")
@PropertySource("classpath:application.properties")
public class RedisConfiguration {
	
	 @Bean
	    JedisConnectionFactory jedisConnectionFactory() {
	        return new JedisConnectionFactory();
	    }

	    @Bean
	    public RedisTemplate<String, Object> redisTemplate() {
	        final RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
	        template.setConnectionFactory(jedisConnectionFactory());
	        template.setValueSerializer(new GenericToStringSerializer<Object>(Object.class));
	        
	        RedisSerializer<String> stringSerializer = new StringRedisSerializer();
	        template.setKeySerializer(stringSerializer);
	        template.setValueSerializer(stringSerializer);
	        template.setHashKeySerializer(stringSerializer);
	        template.setHashValueSerializer(stringSerializer);
	        
	        return template;
	    }

	    @Bean
	    MessageListenerAdapter messageListener() {
	        return new MessageListenerAdapter(new MessageSubscriber());
	    }

	    @Bean
	    RedisMessageListenerContainer redisContainer() {
	        final RedisMessageListenerContainer container = new RedisMessageListenerContainer();
	        container.setConnectionFactory(jedisConnectionFactory());
	        container.addMessageListener(messageListener(), topic());
	        return container;
	    }

	    @Bean
	    MessagePublisher redisPublisher() {
	        return (MessagePublisher) new MessagePublisherImplementation(redisTemplate(), topic());
	    }

	    @Bean
	    ChannelTopic topic() {
	        return new ChannelTopic("pubsub:queue");
	    }
	
}
