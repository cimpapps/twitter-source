package com.thejavacademy.twittersource.serializers;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.thejavacademy.twittersource.avro.models.Tweet;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Serializer;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public class TweetSerializer implements Serializer<Tweet> {


    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public byte[] serialize(String topic, Tweet data) {
        byte[] bytes = new byte[0];

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
            bytes = objectMapper.writeValueAsString(data).getBytes();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bytes;
    }

    @Override
    public byte[] serialize(String topic, Headers headers, Tweet data) {
        System.out.println("Am folosit serializarea noua");
        return serialize(topic, data);
    }

    @Override
    public void close() {

    }
}
