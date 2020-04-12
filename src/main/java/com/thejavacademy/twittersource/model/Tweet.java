package com.thejavacademy.twittersource.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@ToString
public class Tweet {

    private long id;
    private String source;
    private String text;
}
