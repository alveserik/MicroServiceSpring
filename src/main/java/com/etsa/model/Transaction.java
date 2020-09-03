package com.etsa.model;

import java.io.Serializable;

import org.json.JSONObject;

public class Transaction implements Serializable {

	private static final long serialVersionUID = 1L;

	public Transaction(JSONObject element) {
		this.id = element.get("id").toString();
		this.accountId = ((JSONObject) element.get("this_account")).get("id").toString();
		this.counterpartyAccount = ((JSONObject) element.get("other_account")).get("number").toString();
		this.counterpartyName = ((JSONObject) ((JSONObject) element.get("other_account")).get("holder")).get("name")
				.toString();
		this.counterPartyLogoPath = ((JSONObject) ((JSONObject) element.get("other_account")).get("metadata"))
				.get("image_URL").toString();
		this.instructedAmount = ((JSONObject) ((JSONObject) element.get("details")).get("value")).get("amount")
				.toString();
		this.instructedCurrency = ((JSONObject) ((JSONObject) element.get("details")).get("value")).get("currency")
				.toString();
		this.transactionAmount = ((JSONObject) ((JSONObject) element.get("details")).get("value")).get("amount")
				.toString();
		this.transactionCurrency = ((JSONObject) ((JSONObject) element.get("details")).get("value")).get("currency")
				.toString();
		this.transactionType = ((JSONObject) element.get("details")).get("type").toString();
		this.description = ((JSONObject) element.get("details")).get("description").toString();
	}

	private String id;

	private String accountId;

	private String counterpartyAccount;

	private String counterpartyName;

	private String counterPartyLogoPath;

	private String instructedAmount;

	private String instructedCurrency;

	private String transactionAmount;

	private String transactionCurrency;

	private String transactionType;

	private String description;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getCounterpartyAccount() {
		return counterpartyAccount;
	}

	public void setCounterpartyAccount(String counterpartyAccount) {
		this.counterpartyAccount = counterpartyAccount;
	}

	public String getCounterpartyName() {
		return counterpartyName;
	}

	public void setCounterpartyName(String counterpartyName) {
		this.counterpartyName = counterpartyName;
	}

	public String getCounterPartyLogoPath() {
		return counterPartyLogoPath;
	}

	public void setCounterPartyLogoPath(String counterPartyLogoPath) {
		this.counterPartyLogoPath = counterPartyLogoPath;
	}

	public String getInstructedAmount() {
		return instructedAmount;
	}

	public void setInstructedAmount(String instructedAmount) {
		this.instructedAmount = instructedAmount;
	}

	public String getInstructedCurrency() {
		return instructedCurrency;
	}

	public void setInstructedCurrency(String instructedCurrency) {
		this.instructedCurrency = instructedCurrency;
	}

	public String getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(String transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public String getTransactionCurrency() {
		return transactionCurrency;
	}

	public void setTransactionCurrency(String transactionCurrency) {
		this.transactionCurrency = transactionCurrency;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", accountId=" + accountId + ", counterpartyAccount=" + counterpartyAccount
				+ ", counterpartyName=" + counterpartyName + ", counterPartyLogoPath=" + counterPartyLogoPath
				+ ", instructedAmount=" + instructedAmount + ", instructedCurrency=" + instructedCurrency
				+ ", transactionAmount=" + transactionAmount + ", transactionCurrency=" + transactionCurrency
				+ ", transactionType=" + transactionType + ", description=" + description + "]";
	}

}
