package com.thejavacademy.twitterproducer.connector;


import com.thejavacademy.twitterproducer.kafka.TweetsProducer;
import com.thejavacademy.twittersource.avro.models.Tweet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Collections;

@Service
public class TwitterService {


    private TweetsProducer tweetsProducer;

    public TwitterService( TweetsProducer tweetsProducer) {
        this.tweetsProducer = tweetsProducer;
    }

    @Autowired
    public void run() {
        Flux.interval(Duration.ofSeconds(5))
                .map(t -> {
                    return Tweet.newBuilder().setText("da").setSource("da").setId(t.longValue()).build();
                }).subscribe(t -> tweetsProducer.publishCoronaTweets(t));
    }
}
