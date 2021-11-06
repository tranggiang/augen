package com.example.augen.demo.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface PriceChangeStreams {
	String INPUT = "price-in";
	String OUTPUT = "price-out";

	@Input(INPUT)
	SubscribableChannel inboundPrice();

	@Output(OUTPUT)
	MessageChannel outboundPrice();
}
