package com.webapibring.service;

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

import com.webapibring.model.Account;

public class TransactionService {

	public List<Account> getListTransactions() {
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
			System.out.println(output);
			output= "[{\"id\":\"58aeed54-7042-456d-af86-f517bff5b7af\",\"this_account\":{\"id\":\"savings-kids-john\",\"holders\":[{\"name\":\"Savings - Kids John\",\"is_alias\":false}],\"number\":\"832425-00304050\",\"kind\":\"savings\",\"IBAN\":null,\"swift_bic\":null,\"bank\":{\"national_identifier\":\"rbs\",\"name\":\"The Royal Bank of Scotland\"}},\"other_account\":{\"id\":\"5780MRN4uBIgWYmWAhI3pdqbSpItvOw4culXP5FWHJA\",\"holder\":{\"name\":\"ALIAS_03C57D\",\"is_alias\":true},\"number\":\"savings-kids-john\",\"kind\":\"AC\",\"IBAN\":\"4930396\",\"swift_bic\":null,\"bank\":{\"national_identifier\":null,\"name\":\"rbs\"},\"metadata\":{\"public_alias\":null,\"private_alias\":null,\"more_info\":null,\"URL\":null,\"image_URL\":null,\"open_corporates_URL\":null,\"corporate_location\":null,\"physical_location\":null}},\"details\":{\"type\":\"SEPA\",\"description\":\"This is a SEPA Transaction Request\",\"posted\":\"2020-06-05T08:28:38Z\",\"completed\":\"2020-06-05T08:28:38Z\",\"new_balance\":{\"currency\":\"GBP\",\"amount\":null},\"value\":{\"currency\":\"GBP\",\"amount\":\"8.60\"}},\"metadata\":{\"narrative\":null,\"comments\":[],\"tags\":[],\"images\":[],\"where\":null}},{\"id\":\"e22b7066-d02f-41fa-a84f-5dbfcc39e307\",\"this_account\":{\"id\":\"savings-kids-john\",\"holders\":[{\"name\":\"Savings - Kids John\",\"is_alias\":false}],\"number\":\"832425-00304050\",\"kind\":\"savings\",\"IBAN\":null,\"swift_bic\":null,\"bank\":{\"national_identifier\":\"rbs\",\"name\":\"The Royal Bank of Scotland\"}},\"other_account\":{\"id\":\"5780MRN4uBIgWYmWAhI3pdqbSpItvOw4culXP5FWHJA\",\"holder\":{\"name\":\"ALIAS_03C57D\",\"is_alias\":true},\"number\":\"savings-kids-john\",\"kind\":\"AC\",\"IBAN\":\"4930396\",\"swift_bic\":null,\"bank\":{\"national_identifier\":null,\"name\":\"rbs\"},\"metadata\":{\"public_alias\":null,\"private_alias\":null,\"more_info\":null,\"URL\":null,\"image_URL\":null,\"open_corporates_URL\":null,\"corporate_location\":null,\"physical_location\":null}},\"details\":{\"type\":\"SEPA\",\"description\":\"This is a SEPA Transaction Request\",\"posted\":\"2020-06-05T08:15:58Z\",\"completed\":\"2020-06-05T08:15:58Z\",\"new_balance\":{\"currency\":\"GBP\",\"amount\":null},\"value\":{\"currency\":\"GBP\",\"amount\":\"8.60\"}},\"metadata\":{\"narrative\":null,\"comments\":[],\"tags\":[],\"images\":[],\"where\":null}},{\"id\":\"e69c0f86-86c8-423b-8fcb-071cbd957997\",\"this_account\":{\"id\":\"savings-kids-john\",\"holders\":[{\"name\":\"Savings - Kids John\",\"is_alias\":false}],\"number\":\"832425-00304050\",\"kind\":\"savings\",\"IBAN\":null,\"swift_bic\":null,\"bank\":{\"national_identifier\":\"rbs\",\"name\":\"The Royal Bank of Scotland\"}},\"other_account\":{\"id\":\"5780MRN4uBIgWYmWAhI3pdqbSpItvOw4culXP5FWHJA\",\"holder\":{\"name\":\"ALIAS_03C57D\",\"is_alias\":true},\"number\":\"savings-kids-john\",\"kind\":\"AC\",\"IBAN\":\"4930396\",\"swift_bic\":null,\"bank\":{\"national_identifier\":null,\"name\":\"rbs\"},\"metadata\":{\"public_alias\":null,\"private_alias\":null,\"more_info\":null,\"URL\":null,\"image_URL\":null,\"open_corporates_URL\":null,\"corporate_location\":null,\"physical_location\":null}},\"details\":{\"type\":\"SEPA\",\"description\":\"This is a SEPA Transaction Request\",\"posted\":\"2020-06-05T08:00:11Z\",\"completed\":\"2020-06-05T08:00:11Z\",\"new_balance\":{\"currency\":\"GBP\",\"amount\":null},\"value\":{\"currency\":\"GBP\",\"amount\":\"8.60\"}},\"metadata\":{\"narrative\":null,\"comments\":[],\"tags\":[],\"images\":[],\"where\":null}}]";
			JSONArray jsonArray = new JSONArray(output);
			List<JSONObject> jsonItems = IntStream.range(0, jsonArray.length())
		            .mapToObj(index -> (JSONObject) jsonArray.get(index))
		            .collect(Collectors.toList());
			List<Account> accountList = new ArrayList<Account>();
			jsonItems.forEach(arrayElement ->{
				Account account = new Account(arrayElement);
				accountList.add(account);
			});
			conn.disconnect();
			return accountList;
		} catch (Exception e) {
			System.out.println("Exception in NetClientGet:- " + e);
			return null;
		}
	}
		
	public List<Account> getListByType(String type) {
		try {
			List<Account> accounts = getListTransactions(); 
			List<Account> accountsFilterByType = accounts.stream()
				    .filter(account -> account.getTransactionType().equals(type)).collect(Collectors.toList());
			return accountsFilterByType;
		} catch  (Exception e) {
			System.out.println("Exception in NetClientGet:- " + e);
			return null;
		}
		
	}
	
	public Map<String, String> getTotalTransactionByType(String type) {
		try {
			List<Account> accounts = getListTransactions(); 
			List<Account> accountsFilterByType = accounts.stream()
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
