package com.bank.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorDetails> handleAllException(Exception ex, WebRequest request) throws Exception {
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(),
				request.getDescription(false));

		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public final ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request)
			throws Exception {
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(),
				request.getDescription(false));

		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);

	}
	@ExceptionHandler(EmptyListException.class)
	public final ResponseEntity<ErrorDetails> handleEmptyListException(EmptyListException ex, WebRequest request)
			throws Exception {
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(),
				request.getDescription(false));

		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);

	}
	@ExceptionHandler(InSufficientFundException.class)
	public final ResponseEntity<ErrorDetails> handleInSufficientFundException(InSufficientFundException ex, WebRequest request)
	        throws Exception {
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(),
				request.getDescription(false));

		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.PAYMENT_REQUIRED);

	}
	@ExceptionHandler(InvalidFromAccountException.class)
	public final ResponseEntity<ErrorDetails> handleInvalidFromAccountException(InvalidFromAccountException ex, WebRequest request)
	        throws Exception {
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(),
				request.getDescription(false));

		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.UNAUTHORIZED);

	}
	@ExceptionHandler(InvalidToAccountException.class)
	public final ResponseEntity<ErrorDetails> handleInvalidToAccountException(InvalidToAccountException ex, WebRequest request)
	        throws Exception {
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(),
				request.getDescription(false));

		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.UNAUTHORIZED);

	}
}
