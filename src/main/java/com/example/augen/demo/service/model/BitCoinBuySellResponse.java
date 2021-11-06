package com.example.augen.demo.service.model;

import java.text.DecimalFormat;

public class BitCoinBuySellResponse {
	private static final DecimalFormat df = new DecimalFormat("0.00");

	private long id;
	private double spotPrice;
	private double profitFactor;
	private double amount;
	private double totalPrice;
	private String currency;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getSpotPrice() {
		return spotPrice;
	}

	public void setSpotPrice(double spotPrice) {
		this.spotPrice = spotPrice;
	}

	public double getProfitFactor() {
		return profitFactor;
	}

	public void setProfitFactor(double profitFactor) {
		this.profitFactor = profitFactor;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = Double.valueOf(df.format(totalPrice));
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BitCoinBuySellResponse other = (BitCoinBuySellResponse) obj;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (currency == null) {
			if (other.currency != null)
				return false;
		} else if (!currency.equals(other.currency))
			return false;
		if (id != other.id)
			return false;
		if (Double.doubleToLongBits(profitFactor) != Double.doubleToLongBits(other.profitFactor))
			return false;
		if (Double.doubleToLongBits(spotPrice) != Double.doubleToLongBits(other.spotPrice))
			return false;
		if (Double.doubleToLongBits(totalPrice) != Double.doubleToLongBits(other.totalPrice))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BitCoinBuySellResponse [id=" + id + ", spotPrice=" + spotPrice + ", profitFactor=" + profitFactor
				+ ", amount=" + amount + ", totalPrice=" + totalPrice + ", currency=" + currency + "]";
	}

}
