package com.microservices.currencyexchangeservice.exception;

import java.sql.Timestamp;

public class ErrorMessage {
	
	private Timestamp timestamp;
    private String error;
	private String message;
	private int status;
	private String path;

	public ErrorMessage() {
	}

	public ErrorMessage(Timestamp timestamp, String error, String message, int status, String path) {
		this.timestamp = timestamp;
		this.error = error;
		this.message = message;
		this.status = status;
		this.path = path;
	}

	public String getMessage() {
		return message;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public String getError() {
		return error;
	}

	public int getStatus() {
		return status;
	}

	public String getPath() {
		return path;
	}
	
}
