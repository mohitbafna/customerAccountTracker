package com.bank.service;

import java.util.List;
import java.util.Optional;

import com.bank.model.Account;
import com.bank.model.Account.Type;

public interface AccountService {

	public List<Account> getAllAccounts();

	public Account addAccount(Account acc);

	public void removeAccount(long id);

	public Optional<Account> getAccountDetailsById(long id);

	public Optional<Double> getBalanceOf(long id) throws Exception;

	public List<Account> findByType(Type type);

}
