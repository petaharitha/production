package com.bluepal.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

@Configuration
public class RedisListenerConfig<CacheSyncService> {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Bean
    public RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory, 
                                                    MessageListenerAdapter messageListenerAdapter) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(messageListenerAdapter, new ChannelTopic("sparesCache"));
        return container;
    }

    @Bean
    public MessageListenerAdapter messageListenerAdapter(CacheSyncService cacheSyncService) {
        return new MessageListenerAdapter(cacheSyncService, "handleCacheUpdate");
    }
    
    @Bean
    public ChannelTopic channelTopic() {
        return new ChannelTopic("sparesCache"); // Alternatively, externalize this value via application properties
    }
}

