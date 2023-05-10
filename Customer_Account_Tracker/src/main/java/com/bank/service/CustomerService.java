package com.bank.service;

import java.util.List;
import java.util.Optional;

import com.bank.model.Customer;

public interface CustomerService {

	public List<Customer> getAllCustomers();

	public Optional<Customer> getCustomer(long id);

	public void addcustomer(Customer cus);

	public void updateCustomer(long id, Customer cus);

	public void removeCustomer(long id);

	public List<Customer> searchByFirstName(String fName);
	
	public List<Customer> searchByLastName(String lName);
}
