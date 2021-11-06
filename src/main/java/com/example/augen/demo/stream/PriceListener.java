package com.example.augen.demo.stream;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.example.augen.demo.service.model.BitCoinBuySellResponse;

@Component
public class PriceListener {
	// Key:currency ,Value :Spot price
	private Map<String, Double> spotPrice = new ConcurrentHashMap<>();
	// Key:currency ,Value :profit factor
	private Map<String, Double> profitFactor = new ConcurrentHashMap<>();

	@StreamListener(PriceChangeStreams.INPUT)
	public void handlePrice(@Payload BitCoinBuySellResponse newPrice) {

		String currency = newPrice.getCurrency();
		double newSpotPrice = newPrice.getSpotPrice();
		if (spotPrice.get(currency) == null) {
			spotPrice.put(currency, newSpotPrice);
		}

		if (newSpotPrice != spotPrice.get(currency)) {
			System.out.println("Spot price change from : " + spotPrice.get(currency) + " to:  " + newSpotPrice
					+ " for currency " + currency);
			spotPrice.put(currency, newSpotPrice);
		}
		double newProfitFactor = newPrice.getProfitFactor();

		if (profitFactor.get(currency) == null) {
			profitFactor.put(currency, newProfitFactor);
		}

		if (newProfitFactor != profitFactor.get(currency)) {
			System.out.println("Profit factor change from : " + profitFactor.get(currency) + " to:  " + newProfitFactor
					+ " for currency " + currency);
			profitFactor.put(currency, newProfitFactor);
		}

	}
}
