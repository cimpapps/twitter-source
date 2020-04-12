package com.thejavacademy.twittersource.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thejavacademy.twittersource.model.Tweet;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.util.concurrent.SuccessCallback;

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

    public void publishCoronaTweets(String message) {
        final Tweet tweet;
        try {
            tweet = objectMapper.readValue(message, Tweet.class);
            ProducerRecord<String, Tweet> record = new ProducerRecord<>(topic, tweet);
            kafkaTemplate.send(record);
            final Message<Tweet> message1 = MessageBuilder.withPayload(tweet).setHeader(KafkaHeaders.TOPIC, topic).build();

            kafkaTemplate.send(message1);
        } catch (JsonProcessingException e) {


        }

    }

}
