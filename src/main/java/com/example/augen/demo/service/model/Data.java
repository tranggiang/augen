package com.example.augen.demo.service.model;

public class Data {

	private String base;
	private String currency;
	private double amount;

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Data [base=" + base + ", currency=" + currency + ", amount=" + amount + "]";
	}

}