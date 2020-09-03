package com.etsa.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.json.JSONArray;
import org.json.JSONObject;

import com.etsa.model.Transaction;

public class TransactionService {

	public List<Transaction> getListTransactions() {
		try {
			URL url = new URL(
					"https://apisandbox.openbankproject.com/obp/v1.2.1/banks/rbs/accounts/savings-kids-john/public/transactions");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP Error code : " + conn.getResponseCode());
			}
			InputStreamReader in = new InputStreamReader(conn.getInputStream());
			BufferedReader br = new BufferedReader(in);
			String output= br.readLine();
			output = output.substring(output.indexOf(":") + 1);
			output = output.substring(0, output.length() -1 );
			JSONArray jsonArray = new JSONArray(output);
			List<JSONObject> jsonItems = IntStream.range(0, jsonArray.length())
		            .mapToObj(index -> (JSONObject) jsonArray.get(index))
		            .collect(Collectors.toList());
			List<Transaction> accountList = new ArrayList<Transaction>();
			jsonItems.forEach(arrayElement ->{
				Transaction account = new Transaction(arrayElement);
				accountList.add(account);
			});
			conn.disconnect();
			return accountList;
		} catch (Exception e) {
			System.out.println("Exception in NetClientGet:- " + e);
			return null;
		}
	}
		
	public List<Transaction> getListByType(String type) {
		try {
			List<Transaction> accounts = getListTransactions(); 
			List<Transaction> accountsFilterByType = accounts.stream()
				    .filter(account -> account.getTransactionType().equals(type)).collect(Collectors.toList());
			return accountsFilterByType;
		} catch  (Exception e) {
			System.out.println("Exception in NetClientGet:- " + e);
			return null;
		}
		
	}
	
	public Map<String, String> getTotalTransactionByType(String type) {
		try {
			List<Transaction> accounts = getListTransactions(); 
			List<Transaction> accountsFilterByType = accounts.stream()
				    .filter(account -> account.getTransactionType().equals(type)).collect(Collectors.toList());
			Map<String, String> values =  new HashMap<String, String>();
			values.put("transactionType", type);
			values.put("totalAmount", Integer.toString(accountsFilterByType.size()));
			return values;
		} catch  (Exception e) {
			System.out.println("Exception in NetClientGet:- " + e);
			return null;
		}
		
	}

}
