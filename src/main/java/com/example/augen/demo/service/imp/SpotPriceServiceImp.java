package com.example.augen.demo.service.imp;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.example.augen.demo.service.SpotPriceService;
import com.example.augen.demo.service.model.SpotPrice;

@Service
public class SpotPriceServiceImp implements SpotPriceService {
	private static final Logger logger = LoggerFactory.getLogger(SpotPriceServiceImp.class);

	private final RestTemplate restTemplate;
	private static final String URL = "https://api.coinbase.com/v2/prices/spot?currency=";
	private Map<String, SpotPrice> spotPrice = new ConcurrentHashMap<>();

	@Autowired
	public SpotPriceServiceImp(RestTemplateBuilder builder) {
		this.restTemplate = builder.build();
	}

	@Override
	public SpotPrice getSpotPrice(String currency) {
		if (spotPrice.containsKey(currency)) {
			return spotPrice.get(currency);
		} else {
			SpotPrice spotPriceByCurrency = getSpotPriceByCurrency(currency);
			spotPrice.put(currency, spotPriceByCurrency);
			return spotPriceByCurrency;
		}

	}

	// run every 1 sec
	@Scheduled(fixedRate = 1000)
	private void getSpotPriceInterval() {
		for (String currency : spotPrice.keySet()) {
			spotPrice.put(currency, getSpotPriceByCurrency(currency));
		}
	}

	private SpotPrice getSpotPriceByCurrency(String currency) {
		try {
			return this.restTemplate.getForObject(URL + currency, SpotPrice.class);
		} catch (RestClientException e) {
			logger.debug("Exception when getting price from currency : " + currency);
			logger.debug(e.getMessage());
			// can not connect to remote service , return null as default
			return null;
		}

	}

}
