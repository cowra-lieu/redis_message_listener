package com.btw.demo.redis_message_listener;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

@Configuration
@PropertySource("classpath:/redis.properties")
public class RedisConfig {

    private Environment env;

    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        RedisStandaloneConfiguration rsc = new RedisStandaloneConfiguration();
        rsc.setHostName(env.getRequiredProperty("redis.host"));
        rsc.setPort(env.getRequiredProperty("redis.port", int.class));

        String password = env.getProperty("redis.password");
        if (password != null && password.trim().length() > 0) {
            rsc.setPassword(password);
        }
        return new JedisConnectionFactory(rsc);
    }

    @Bean
    ChannelTopic icer_update_topic() {
        return new ChannelTopic("icer:update");
    }

    @Bean
    MessageListenerAdapter messageListener() {
        return new MessageListenerAdapter( new IcerUpdateListener() );
    }

    @Bean
    RedisMessageListenerContainer redisContainer() {
        final RedisMessageListenerContainer container = new RedisMessageListenerContainer();

        container.setConnectionFactory( jedisConnectionFactory() );
        container.addMessageListener( messageListener(), icer_update_topic() );

        return container;
    }

    @Autowired
    public void setEnv(Environment env) {
        this.env = env;
    }
}
