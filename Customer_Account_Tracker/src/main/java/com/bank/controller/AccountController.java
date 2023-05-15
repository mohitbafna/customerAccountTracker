package com.bank.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.bank.exception.*;
import com.bank.model.Account;
import com.bank.model.Account.Type;
import com.bank.service.AccountServiceImp;

@RestController
@RequestMapping("/bank")
public class AccountController {

	@Autowired
	AccountServiceImp accountService;

	@GetMapping("/accounts")
	public ResponseEntity<List<Account>> getAllAccounts() {
		try {
			List<Account> accountList = accountService.getAllAccounts();
			if (accountList.isEmpty()) {
				throw new EmptyListException("Account list is empty");
			}
			return new ResponseEntity<List<Account>>(accountList, new HttpHeaders(), HttpStatus.OK);
		} catch (EmptyListException e) {
			throw new EmptyListException("Customer list is empty");
		} catch (Exception e) {
			return new ResponseEntity("Internal Server Error", new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/accounts/{id}")
	public ResponseEntity<Optional<Account>> accountDetail(@PathVariable long id) {
		try {
			Optional<Account> account = accountService.getAccountDetailsById(id);
			if (account.isPresent()) {
				return new ResponseEntity<Optional<Account>>(account, new HttpHeaders(), HttpStatus.OK);
			} else {
				throw new ResourceNotFoundException("Customer not found with account number " + id);
			}
		} catch (ResourceNotFoundException e) {
			throw new ResourceNotFoundException("Account not found with account number " + id);
		} catch (Exception e) {
			return new ResponseEntity("Internal Server Error", new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/accounts/balanceof/{id}")
	public ResponseEntity<Double> BalanceOfAccount(@PathVariable long id) throws Exception {
		try {
			Double balance = accountService.getBalanceOf(id).get();
			if (accountService.getAccountDetailsById(id).isPresent()) {
				return new ResponseEntity<Double>(balance, new HttpHeaders(), HttpStatus.OK);
			} else {
				throw new ResourceNotFoundException("Account not found with account number " + id);
			}
		} catch (ResourceNotFoundException e) {
			throw new ResourceNotFoundException("Account not found with account number " + id);
		} catch (Exception e) {
			return new ResponseEntity("Internal Server Error", new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/accounts/type")
	public ResponseEntity<List<Account>> findAccountsByType(@RequestParam Type type) {
		try {
			List<Account> accountTypeList = accountService.findByType(type);
			if (accountTypeList.isEmpty())
				return new ResponseEntity<List<Account>>(accountTypeList, new HttpHeaders(), HttpStatus.NO_CONTENT);
			else
				return new ResponseEntity<List<Account>>(accountTypeList, new HttpHeaders(), HttpStatus.OK);
		} catch (ResourceNotFoundException e) {
			throw new ResourceNotFoundException("Account not found of Type " + type);
		} catch (Exception e) {
			return new ResponseEntity("Internal Server Error", new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

//	@PostMapping("/accounts")
//	public ResponseEntity<Account> createAccount(@RequestBody Account acc) {
//		Account savedAccount= accountService.addAccount(acc);
//		
//		URI location= ServletUriComponentsBuilder.fromCurrentRequest()
//				.path("/{id}")
//				.buildAndExpand(savedAccount.getAccount_number())
//				.toUri() ;
//		
//		return ResponseEntity.created(location).build()  ;
//		/*
//		 * =================INPUT BODY======================== JSON Format 
//		 * { 
//		   "balance": "1200" , 
//		   "type": "saving",
//		   "customer_detail" :{ 
//		                 "cId": 2 
//		                 } 
//		   }
//		 * 
//		 */
//	}

	@PostMapping("/accounts")
	public ResponseEntity<String> createAccount(@RequestBody Account acc) {
		try {
			accountService.addAccount(acc);
			return new ResponseEntity<String>("Account Opened", new HttpHeaders(), HttpStatus.CREATED);
		} catch (Exception e) {
			// e.printStackTrace() ;
			return new ResponseEntity<String>("Internal Server Error", new HttpHeaders(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/accounts/{id}")
	public ResponseEntity<String> removeAccount(@PathVariable long id) {
		accountService.removeAccount(id);
		return new ResponseEntity<String>("Account closed permanently", new HttpHeaders(), HttpStatus.OK);
	}
}
