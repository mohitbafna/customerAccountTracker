package com.bank.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name="seq", initialValue=1001)
public class Account {
	
	public enum Type {
		SAVING, CURRENT, FD ;
	}
	
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
	private long account_number ;
    
    private double balance ;
    
    @Column(nullable=false)
    @Enumerated(EnumType.STRING)
    private Type type ;	
    
   // @JsonIgnoreProperties({"customer_details"})
  ///we need to add @JsonIgnore.to avoid stackoverflow/endless nested
   // json serialization when doing a GET Customer(localhost:8080/bank/customers/1)
    
     	@JsonIgnore 
    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer_detail ;

	public Account() {
		super();
	}
	

//	public Account( double balance, Type type) {
//		super();
//		this.balance = balance;
//		this.type = type;
//	}
//
//	public Account(long account_number, double balance, Type type) {
//		super();
//		this.account_number = account_number;
//		this.balance = balance;
//		this.type = type;
//	}
	public Account(long account_number, double balance, Type type, Customer customer_detail) {
		super();
		this.account_number = account_number;
		this.balance = balance;
		this.type = type;
		this.customer_detail = customer_detail;
	}

	public long getAccount_number() {
		return account_number;
	}

	public void setAccount_number(long account_number) {
		this.account_number = account_number;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Customer getCustomer_detail() {
		return customer_detail;
	}

	public void setCustomer_detail(Customer customer_detail) {
		this.customer_detail = customer_detail;
	}

	@Override
	public String toString() {
		return "Account [account_number=" + account_number + ", balance=" + balance + ", type=" + type
				+ ", customer_detail=" + customer_detail + "]";
	}
    
}
