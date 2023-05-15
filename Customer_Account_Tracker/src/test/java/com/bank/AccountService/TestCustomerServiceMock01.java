package com.bank.AccountService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.*;

import org.junit.jupiter.api.*;

import com.bank.model.Customer;
import com.bank.repository.CustomerRepository;
import com.bank.service.CustomerServiceImp;

public class TestCustomerServiceMock01 {
	private CustomerServiceImp customerServiceMock ;
	private CustomerRepository c_repoMock ;
	
	Customer customer1=new Customer(1,"Mohit","Bafna",1234);
	Customer customer2=new Customer(2,"Konica","Dalal",2345);
	Customer customer3=new Customer(3,"Mohit","Bafna",3456);
	Customer customer4=new Customer(4,"Mohit","Bafna",4567);
	
	@BeforeEach
	void setup() {
		this.c_repoMock=mock(CustomerRepository.class) ;	
		this.customerServiceMock=new CustomerServiceImp(c_repoMock);
	}
	
	@Test
	void testRetrieveAllCustomers() {
		//given
		List<Customer> list=new ArrayList<>() ;
		list.add(customer1);
		list.add(customer2);
		list.add(customer3);
		list.add(customer4);
		when(c_repoMock.findAll()).thenReturn(list) ;
		//when
		List<Customer> actual=customerServiceMock.getAllCustomers() ;
		//then
		assertEquals(list.size(), actual.size());
		assertEquals(list.get(0), actual.get(0));
		assertEquals(list.get(3), actual.get(3));
		verify(c_repoMock, times(1)).findAll();
	}
}
