package com.example.augen.demo.service.model;

public class SpotPrice {
	private Data data;

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

	public String getBase() {
		return this.data.getBase();
	}

	public String getCurrency() {
		return this.data.getCurrency();
	}

	public double getAmount() {
		return this.data.getAmount();
	}

	@Override
	public String toString() {
		return "SpotPrice [data=" + data + "]";
	}

}
