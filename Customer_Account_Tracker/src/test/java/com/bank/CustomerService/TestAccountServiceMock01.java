package com.bank.CustomerService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.*;

import org.junit.jupiter.api.*;

import com.bank.model.*;
import com.bank.repository.*;
import com.bank.service.AccountServiceImp;

public class TestAccountServiceMock01 {   //not working 14/5/2023
	private AccountServiceImp accountServiceMock ;
	private CustomerRepository c_repoMock ;
	private AccountRepository a_repoMock ;
	
	Customer cus ;
	Account account1=new Account(1001,2000.0,"CURRENT",1);
	Account account2=new Account(1002,3000.0,"CURRENT",2);
	Account account3=new Account(1003,4000.0,"SAVING",3);
	Account account4=new Account(1004,50000.0,"FD",1);
	
	@BeforeEach
	void setup() {
		this.c_repoMock=mock(CustomerRepository.class) ;
		this.a_repoMock=mock(AccountRepository.class) ;
		this.accountServiceMock=new AccountServiceImp(a_repoMock, c_repoMock);
	}
	
	@Test
	void testRetrieveCustomerCount() {
		//given
		List<Account> list=new ArrayList<>() ;
		list.add(account1);
		list.add(account2);
		list.add(account3);
		list.add(account4);
		when(a_repoMock.findAll()).thenReturn(list) ;
		long expected= 1002 ;
		//when
		long actual=accountServiceMock.getAllAccounts().get(1).getAccount_number() ;
		//then
		assertEquals(expected, actual);
	}
}
