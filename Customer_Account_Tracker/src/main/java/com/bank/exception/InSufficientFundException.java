package com.bank.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.PAYMENT_REQUIRED)
public class InSufficientFundException extends RuntimeException {
	public InSufficientFundException(String message) {
		super(message) ;
	}
}
