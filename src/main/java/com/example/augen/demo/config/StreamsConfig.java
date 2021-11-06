package com.example.augen.demo.config;

import org.springframework.cloud.stream.annotation.EnableBinding;

import com.example.augen.demo.stream.PriceChangeStreams;

@EnableBinding(PriceChangeStreams.class)
public class StreamsConfig {

}
