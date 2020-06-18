package com.btw.demo.redis_message_listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

public class IcerUpdateListener implements MessageListener {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onMessage(final Message message, final byte[] pattern ) {
        System.out.println( "Message received: " + message.toString() );
        try {
            IcerUpdate icerUpdate = objectMapper.readValue(message.toString(), IcerUpdate.class);
            System.out.println(icerUpdate.isSmallUpdate());
            System.out.println(icerUpdate.getRawDataList() != null ? icerUpdate.getRawDataList().size() : null);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }

}
