package com.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bank.service.TransactionService;

@RestController
public class TransactionController {
	@Autowired
	private TransactionService transactionService ;
	
	@PutMapping("/customers/accounts/transaction")
	public ResponseEntity<String> transferFunds(@RequestParam long fromAccount, @RequestParam long toAccount, @RequestParam double amount){
		String fund= transactionService.tranferFunds(fromAccount, toAccount, amount) ;
		return new ResponseEntity<String>(fund,new HttpHeaders(), HttpStatus.OK) ;
	}
}
