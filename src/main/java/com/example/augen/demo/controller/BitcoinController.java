package com.example.augen.demo.controller;

import java.util.Currency;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.augen.demo.service.Identifier;
import com.example.augen.demo.service.ProfitFactor;
import com.example.augen.demo.service.SpotPriceService;
import com.example.augen.demo.service.model.BitCoinBuySellResponse;
import com.example.augen.demo.service.model.SpotPrice;
import com.example.augen.demo.stream.PriceChangeStreams;

@RestController
@RequestMapping(path = "/price")
public class BitcoinController {
	@Autowired
	private SpotPriceService spotPriceService;
	@Autowired
	private ProfitFactor profitFactor;
	@Autowired
	private Identifier identifier;

	@Autowired
	private PriceChangeStreams stream;
	private static final Logger logger = LoggerFactory.getLogger(BitcoinController.class);

	@GetMapping("/buy")
	public ResponseEntity<?> buyBitcoin(@RequestParam() double amount,
			@RequestParam(required = false) String currency) {
		return processRequest(amount, currency);
	}

	@GetMapping("/sell")
	public ResponseEntity<?> sellBitcoin(@RequestParam() double amount,
			@RequestParam(required = false) String currency) {
		return processRequest(amount, currency);
	}

	private ResponseEntity<?> processRequest(double amount, String currency) {
		logger.info("receive params amount:  " + amount + " currency : " + currency);

		// default currency
		if (currency == null) {
			currency = "NZD";
		}
		try {
			// validate currency
			Currency.getInstance(currency);

		} catch (Exception e) {
			return new ResponseEntity<>("invalid currency: " + currency, HttpStatus.BAD_REQUEST);
		}
		SpotPrice spotPrice = spotPriceService.getSpotPrice(currency);
		if (spotPrice == null) {
			return new ResponseEntity<>("please try again later. ", HttpStatus.ACCEPTED);
		}
		BitCoinBuySellResponse respone = new BitCoinBuySellResponse();
		respone.setId(identifier.getIdentifier());
		respone.setAmount(amount);
		respone.setSpotPrice(spotPrice.getAmount());
		respone.setProfitFactor(profitFactor.getProfitFactor());
		respone.setTotalPrice(spotPrice.getAmount() * profitFactor.getProfitFactor() * amount);
		respone.setCurrency(spotPrice.getCurrency());

		// send price to Kafka
		MessageChannel messageChannel = stream.outboundPrice();
		messageChannel.send(MessageBuilder.withPayload(respone)
				.setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON).build(), 3000);

		return new ResponseEntity<>(respone, HttpStatus.ACCEPTED);
	}
}
