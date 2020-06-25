package com.btw.demo.redis_message_listener;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

public class CoreDataUpdateListener implements MessageListener {

    @Override
    public void onMessage(Message message, byte[] pattern) {
        System.out.println( "[Core Data] Message received: " + message.toString() );
    }

}
