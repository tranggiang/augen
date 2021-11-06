package com.example.augen.demo.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.example.augen.demo.service.ProfitFactor;

@Service
public class ProfitFactorImp implements ProfitFactor {

	private static Random rand = new Random();
	private static List<Double> profits = new ArrayList<>(3);
	static {
		profits.add(1.05);
		profits.add(1.1);
		profits.add(1.12);

	}

	@Override
	public double getProfitFactor() {
		return profits.get(rand.nextInt(3));
	}

}
