package com.bank.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.model.Customer;
import com.bank.repository.CustomerRepository;

@Service
public class CustomerServiceImp implements CustomerService {
	
	public CustomerRepository customerRepository;
	public CustomerServiceImp(CustomerRepository customerRepository) {
		this.customerRepository=customerRepository ;
	}

	public List<Customer> getAllCustomers() {
		List<Customer> customerList = customerRepository.findAll();
		if (customerList.size() > 0)
			return customerList;
		else
			return new ArrayList<Customer>();
	}

	public Optional<Customer> getCustomer(long id) {

		return customerRepository.findById(id);
	}

	public void addcustomer(Customer cus) {
		customerRepository.save(cus);
	}

	public void updateCustomer(long id, Customer cus) {
		Customer oldEntity = customerRepository.findById(id).get();
		Customer newEntity = new Customer();

		if (cus.getFirstName() != null)
			newEntity.setFirstName(cus.getFirstName());
		else
			newEntity.setFirstName(oldEntity.getFirstName());
		if (cus.getLastName() != null)
			newEntity.setLastName(cus.getLastName());
		else
			newEntity.setLastName(oldEntity.getLastName());
		if (cus.getAadharNumber() != 0)
			newEntity.setAadharNumber(cus.getAadharNumber());
		else
			newEntity.setAadharNumber(oldEntity.getAadharNumber());

		newEntity.setcId(oldEntity.getcId());

		customerRepository.save(newEntity);

//		if (customerRepository.findById(id).isPresent())
//			customerRepository.save(cus);
	}

	public void removeCustomer(long id) {
		if (customerRepository.findById(id).isPresent())
			customerRepository.deleteById(id);
	}

	public List<Customer> searchByFirstName(String fName) {
		return customerRepository.findByFirstName(fName);
	}

	public List<Customer> searchByLastName(String lName) {
		return customerRepository.findByLastName(lName);
	}
	
	
}
