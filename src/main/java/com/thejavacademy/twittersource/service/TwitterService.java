package com.thejavacademy.twittersource.service;


import com.thejavacademy.twittersource.kafka.TweetsProducer;
import com.thejavacademy.twittersource.twitter.connector.TwitterClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.Collections;

@Service
public class TwitterService {

    private TwitterClient twitterClient;

    private TweetsProducer tweetsProducer;

    public TwitterService(TwitterClient twitterClient, TweetsProducer tweetsProducer) {
        this.twitterClient = twitterClient;
        this.tweetsProducer = tweetsProducer;
    }

    public Flux<String> run(String term) {
       return   Flux.push(emitter -> {
            while (true) {
                final String poll = this.twitterClient.poll(Collections.singletonList(term));
                emitter.next(poll);
                tweetsProducer.publishCoronaTweets(poll);
            }
        });
    }
}
