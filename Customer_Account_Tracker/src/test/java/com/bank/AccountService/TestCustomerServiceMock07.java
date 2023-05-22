package com.bank.AccountService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.*;

import org.junit.jupiter.api.*;

import com.bank.model.Customer;
import com.bank.repository.CustomerRepository;
import com.bank.service.CustomerServiceImp;

public class TestCustomerServiceMock07 {
	private CustomerServiceImp customerServiceMock;
	private CustomerRepository c_repoMock;

	Customer customer1 = new Customer(1, "Mohit", "Bafna", 1234);
	Customer customer2 = new Customer(2, "Konica", "Dalal", 2345);
	Customer customer3 = new Customer(3, "Mohit", "Jain", 3456);
	Customer customer4 = new Customer(4, "Konica", "Jain", 4567);
	Customer customer5 = new Customer(5, "Mohit", "Rahi", 5678);

	@BeforeEach
	void setup() {
		this.c_repoMock = mock(CustomerRepository.class);
		this.customerServiceMock = new CustomerServiceImp(c_repoMock);
	}

	@Test
	public void searchByLastName_ReturnsMatchingCustomers() {
		// Mock data
		long customerId = 1L;
		String lastName = "Jain";
		List<Customer> Mlist = new ArrayList<>();
		Mlist.add(customer3);
		Mlist.add(customer4);
		// Mock the behavior of the customer repository
		when(c_repoMock.findByLastName(lastName)).thenReturn(Mlist);

		// Call the method to be tested
		List<Customer> actual = customerServiceMock.searchByLastName(lastName);

		// Assert that the customer has been updated correctly
		assertEquals(2, actual.size());
		assertEquals(Mlist, actual);
	}
}
