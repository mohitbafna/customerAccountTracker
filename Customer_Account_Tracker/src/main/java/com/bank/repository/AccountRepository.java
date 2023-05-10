package com.bank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bank.model.Account;
import com.bank.model.Account.Type;


@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

	@Query("select acc from Account acc where acc.type like ?1")
	 List<Account> findbyAccounts(Type type);
}
