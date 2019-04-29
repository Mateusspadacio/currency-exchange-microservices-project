package com.microservices.currencyexchangeservice.exception;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAdvice {
	
	private ResponseEntity<ErrorMessage> buildErrorMessage(Exception exception, HttpStatus status, HttpServletRequest request) {
		return ResponseEntity.status(status).body(new ErrorMessage(new Timestamp(System.currentTimeMillis()), 
				status.toString(), exception.getMessage(), status.value(), request.getRequestURI()));
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorMessage> genericException(Exception exception, HttpServletRequest request) {
		return buildErrorMessage(exception, HttpStatus.INTERNAL_SERVER_ERROR, request);
	}
	
	@ExceptionHandler(CurrencyExchangeNotFound.class)
	public ResponseEntity<ErrorMessage> currencyExchangeNotFound(CurrencyExchangeNotFound exception, HttpServletRequest request) {
		return buildErrorMessage(exception, HttpStatus.NOT_FOUND, request);
	}
}
