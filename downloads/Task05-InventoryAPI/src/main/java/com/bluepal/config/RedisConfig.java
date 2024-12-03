package com.bluepal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

@Configuration
public class RedisConfig {

    @Bean
    public RedisMessageListenerContainer messageListenerContainer(
            RedisConnectionFactory connectionFactory,
            MessageListenerAdapter listenerAdapter) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(listenerAdapter, new ChannelTopic("cacheSyncChannel"));
        return container;
    }

    @Bean
    public MessageListenerAdapter listenerAdapter(CacheUpdateListener cacheUpdateListener) {
        return new MessageListenerAdapter(cacheUpdateListener, "handleMessage");
    }

    @Bean
    public ChannelTopic topic() {
        return new ChannelTopic("cacheSyncChannel");
    }
}
