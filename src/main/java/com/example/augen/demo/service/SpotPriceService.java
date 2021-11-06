package com.example.augen.demo.service;

import org.springframework.stereotype.Service;

import com.example.augen.demo.service.model.SpotPrice;

@Service
public interface SpotPriceService {
	public SpotPrice getSpotPrice(String currency);
}
