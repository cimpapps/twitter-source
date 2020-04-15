package com.thejavacademy.twitterproducer.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thejavacademy.twittersource.avro.models.Tweet;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class TweetsProducer {

    @Value("${corona.tweets.topic}")
    private String topic;

    @Autowired
    private ObjectMapper objectMapper;

    KafkaTemplate<String, Tweet> kafkaTemplate;

    public TweetsProducer(KafkaTemplate<String, Tweet> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishCoronaTweets(Tweet message) {
        ProducerRecord<String, Tweet> record = new ProducerRecord<>(topic, message);
        kafkaTemplate.send(record).addCallback(System.out::println, Throwable::printStackTrace);
    }

}
