package com.microservices.currencyexchangeservice.exception;

public class CurrencyExchangeNotFound extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CurrencyExchangeNotFound(String message) {
		super(message);
	}
}
