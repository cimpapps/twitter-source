package com.thejavacademy.twittersource.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thejavacademy.twittersource.model.Tweet;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class TweetsConsumer {


    @KafkaListener(topics = "${corona.tweets.topic}")
    public void listenTest(@Payload Tweet message) {
        System.out.println(message);
    }

}
