package com.bank.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.model.Account;
import com.bank.repository.AccountRepository;

@Service
public class TransactionServiceImp implements TransactionService {
	@Autowired
	private AccountRepository accountRepository;

	public String tranferFunds(long fromAccountId, long toAccountId, double Amount) {

		Optional<Account> fromAccount = accountRepository.findById(fromAccountId);
		Optional<Account> toAccount = accountRepository.findById(toAccountId);
		double amount = Amount;

		if (fromAccount.isPresent()) {
			if (toAccount.isPresent()) {
				if (fromAccount.get().getBalance() > amount) {

					Account FromAccount = fromAccount.get();
					Account ToAccount = toAccount.get();

					FromAccount.setBalance(FromAccount.getBalance() - amount);
					ToAccount.setBalance(ToAccount.getBalance() + amount);

					accountRepository.save(FromAccount);
					accountRepository.save(ToAccount);

					return "Transaction Successfull";
				} else
					return "Insufficient balance";
			} else
				return "Wrong Recipient Account Number";
		} else
			return "Invalid Account Id";
	}
}
