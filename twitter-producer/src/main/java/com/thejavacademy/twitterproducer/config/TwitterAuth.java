package com.thejavacademy.twitterproducer.config;


import com.twitter.hbc.httpclient.auth.Authentication;
import com.twitter.hbc.httpclient.auth.OAuth1;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "twitter")
@Data
public class TwitterAuth {

    private String apiKey;
    private String apiSecretKey;
    private String token;
    private String tokenSecret;

    @Bean
    public Authentication auth() {
        return new OAuth1(getApiKey(),
                getApiSecretKey(),
                getToken(),
                getTokenSecret());
    }

}
