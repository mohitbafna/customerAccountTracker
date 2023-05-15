package com.bank.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.model.Account;
import com.bank.model.Account.Type;
import com.bank.model.Customer;
import com.bank.repository.AccountRepository;
import com.bank.repository.CustomerRepository;

@Service
public class AccountServiceImp implements AccountService {

	private AccountRepository accountRepository;
	private CustomerRepository customerRepository;

	public AccountServiceImp(AccountRepository accountRepository, CustomerRepository customerRepository) {
		this.accountRepository = accountRepository;
		this.customerRepository = customerRepository;
	}

	public List<Account> getAllAccounts() {
		List<Account> accountList = accountRepository.findAll();
		if (accountList.isEmpty())
			return new ArrayList<Account>();
		else
			return accountList;
	}

	public Account addAccount(Account acc) {
		Optional<Customer> c2 = customerRepository.findById(acc.getCustomer_detail().getcId());
		if (c2.isEmpty()) {
			customerRepository.save(acc.getCustomer_detail());
			accountRepository.save(acc);
		} else
			accountRepository.save(acc);

		return acc;
	}

	public void removeAccount(long id) {
		accountRepository.deleteById(id);
	}

	public Optional<Account> getAccountDetailsById(long id) {
		return accountRepository.findById(id);
	}

	public Optional<Double> getBalanceOf(long id) throws Exception {
		if (accountRepository.findById(id).isPresent())
			return accountRepository.findById(id).map(Account::getBalance);
		else
			throw new Exception("Account with " + id + " account number not present");

	}

	public List<Account> findByType(Type type) {
		return accountRepository.findbyAccounts(type);
	}

}
