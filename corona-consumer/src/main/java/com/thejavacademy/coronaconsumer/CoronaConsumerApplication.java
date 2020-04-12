package com.thejavacademy.coronaconsumer;

import com.thejavacademy.twittersource.avro.models.Tweet;
import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

@SpringBootApplication
public class CoronaConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoronaConsumerApplication.class, args);


	}

}
