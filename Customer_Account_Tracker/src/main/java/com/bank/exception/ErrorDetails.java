package com.bank.exception;

import java.time.LocalDateTime;

public class ErrorDetails {
	private final String author="Mohit" ;
	private LocalDateTime timestamp;
	private String message;
	private String details;

	public ErrorDetails(LocalDateTime localDateTime, String message, String details) {
		super();
		this.timestamp = localDateTime;
		this.message = message;
		this.details = details;
	}
	public String getAuthor() {
		return author;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public String getMessage() {
		return message;
	}

	public String getDetails() {
		return details;
	}

}
