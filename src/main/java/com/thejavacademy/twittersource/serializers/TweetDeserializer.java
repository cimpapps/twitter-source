package com.thejavacademy.twittersource.serializers;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thejavacademy.twittersource.avro.models.Tweet;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;
import java.util.Map;

public class TweetDeserializer implements Deserializer<Tweet> {


    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public Tweet deserialize(String topic, byte[] data) {
        Tweet tweet = new Tweet();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
        try {
              tweet = objectMapper.readValue(data, Tweet.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tweet;
    }

    @Override
    public Tweet deserialize(String topic, Headers headers, byte[] data) {
        System.out.println("Am folosit dexserializarea noua");

        return deserialize(topic, data);
    }

    @Override
    public void close() {

    }
}
