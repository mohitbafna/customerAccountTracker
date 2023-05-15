package com.bank.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class InvalidFromAccountException extends RuntimeException {
		
	public InvalidFromAccountException(String message){
			super(message) ;
		}
	}