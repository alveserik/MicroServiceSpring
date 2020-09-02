package com.webapibring.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Transactions {

	@JsonProperty("transactions")
	private List<Account> transactions;

	public List<Account> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Account> transactions) {
		this.transactions = transactions;
	}

	@Override
	public String toString() {
		return "Transactions [transactions=" + transactions + "]";
	}
}
 