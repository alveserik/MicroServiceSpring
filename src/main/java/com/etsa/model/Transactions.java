package com.etsa.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Transactions {

	@JsonProperty("transactions")
	private List<Transaction> transactions;

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	@Override
	public String toString() {
		return "Transactions [transactions=" + transactions + "]";
	}
}
 