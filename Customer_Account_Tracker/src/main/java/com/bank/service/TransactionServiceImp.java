package com.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.model.Account;
import com.bank.repository.AccountRepository;

@Service
public class TransactionServiceImp implements TransactionService {
	@Autowired
	private AccountRepository accountRepository;

	public String tranferFunds(long fromAccountId, long toAccountId, double Amount) {

		Account fromAccount = accountRepository.findById(fromAccountId).get();
		Account toAccount = accountRepository.findById(toAccountId).get();
		double amount = Amount;

		fromAccount.setBalance(fromAccount.getBalance() - amount);
		toAccount.setBalance(toAccount.getBalance() + amount);

		accountRepository.save(fromAccount);
		accountRepository.save(toAccount);

		return "Transaction Success : Amount "+amount+" is debited. \nUpdated Balance : "+fromAccount.getBalance();
		
	}
}
