package com.thejavacademy.twittersource;

import com.thejavacademy.twittersource.service.TwitterService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/twitter")
public class TwitterFluxController {

    private TwitterService twitterService;

    public TwitterFluxController(TwitterService twitterService) {
        this.twitterService = twitterService;
    }

    @GetMapping(value = "/{term}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> getTweets(@PathVariable("term") String term) {
         return this.twitterService.run(term);
    }

}
