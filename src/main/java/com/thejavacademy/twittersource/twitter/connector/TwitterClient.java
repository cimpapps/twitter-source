package com.thejavacademy.twittersource.twitter.connector;

import com.twitter.hbc.ClientBuilder;
import com.twitter.hbc.core.Constants;
import com.twitter.hbc.core.endpoint.StatusesFilterEndpoint;
import com.twitter.hbc.core.processor.StringDelimitedProcessor;
import com.twitter.hbc.httpclient.BasicClient;
import com.twitter.hbc.httpclient.auth.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

@Component
public class TwitterClient {


    private ThreadLocal<BasicClient> client = new ThreadLocal<>();

    private BlockingQueue<String> queue;
    private StatusesFilterEndpoint endpoint;
    private ClientBuilder clientBuilder;
    private Authentication auth;

    public TwitterClient(Authentication auth) {
        this.auth = auth;
        endpoint = new StatusesFilterEndpoint();
        endpoint.stallWarnings(false);

    }


    public String poll(List<String> searchTerms) {
        if (client.get() == null) {
            queue = new LinkedBlockingQueue<String>(10000);
            clientBuilder = new ClientBuilder()
                    .name("sampleExampleClient1" + Thread.currentThread())
                    .hosts(Constants.STREAM_HOST)
                    .endpoint(endpoint)
                    .authentication(auth)
                    .processor(new StringDelimitedProcessor(queue));
            client.set(clientBuilder.build());
            endpoint.trackTerms(searchTerms);
            client.get().connect();
        }
        try {
            if (client.get().isDone()) {
                System.out.println("Client connection closed unexpectedly: " + client.get().getExitEvent().getMessage());
            }
            final String poll = queue.poll(10, TimeUnit.SECONDS);
            return poll == null? "NO DATA in the past 10 SECONDS": poll;
        } catch (Exception e) {
            e.printStackTrace();
            client.get().stop();
        }
        return "";
    }



}
