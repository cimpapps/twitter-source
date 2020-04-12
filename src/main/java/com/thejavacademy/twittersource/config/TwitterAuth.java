package com.thejavacademy.twittersource.config;


import com.twitter.hbc.httpclient.auth.Authentication;
import com.twitter.hbc.httpclient.auth.OAuth1;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

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
