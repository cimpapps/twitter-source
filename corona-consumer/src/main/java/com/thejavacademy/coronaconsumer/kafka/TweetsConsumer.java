package com.thejavacademy.coronaconsumer.kafka;

import com.thejavacademy.twittersource.avro.models.Tweet;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class TweetsConsumer {

    @KafkaListener(topics = "${corona.tweets.topic}")
    public void listenTest(ConsumerRecord<String, Tweet> records) {
        System.out.println(records.value());
    }

}
