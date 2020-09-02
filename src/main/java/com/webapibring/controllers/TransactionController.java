package com.webapibring.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.webapibring.model.Account;
import com.webapibring.service.TransactionService;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

	private static TransactionService service = new TransactionService();

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody List<Account> listAllTransactions() {
		return service.getListTransactions();
	}	
	
	@RequestMapping(value = "/getListByType", method = RequestMethod.GET)
	public @ResponseBody List<Account> getListByType(@RequestParam(value = "type") String type) {
		return service.getListByType(type);
	}
	
	@RequestMapping(value = "/getTotalTransactionByType", method = RequestMethod.GET)
	public @ResponseBody Map<String, String> getTotalTransactionByType(@RequestParam(value = "type") String type) {
		return service.getTotalTransactionByType(type);
	}
}
