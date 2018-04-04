package com.msg.mc.kafka;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.msg.mc.vo.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by cloudy on 17/5/31.
 */
@Component
public class MsgProducer {
    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;
    private Gson gson = new GsonBuilder().create();

    public void sendMessage() {
        Message message = new Message();
        message.setId(21);
        message.setPlatformId(2);
        message.setCellphone("13049416910");
        message.setCode("123543");
        System.out.println(gson.toJson(message));
        kafkaTemplate.send("messageCenter",gson.toJson(message));
    }
}
