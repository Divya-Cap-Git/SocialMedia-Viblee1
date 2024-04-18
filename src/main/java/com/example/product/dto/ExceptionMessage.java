package com.example.product.dto;

import java.time.LocalDate;

public class ExceptionMessage {
	private String message;
	
	
	public ExceptionMessage() {}

	public ExceptionMessage(String message) {
		super();
		this.message = message;
		
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	
		
	

}
