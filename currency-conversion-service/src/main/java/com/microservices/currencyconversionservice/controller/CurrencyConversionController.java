package com.microservices.currencyconversionservice.controller;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.currencyconversionservice.bean.CurrencyConversion;
import com.microservices.currencyconversionservice.service.CurrencyConversionService;

@RestController
public class CurrencyConversionController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CurrencyConversionService service;

	@GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
	public ResponseEntity<CurrencyConversion> convertCurrency(
			@PathVariable String from,
			@PathVariable String to,
			@PathVariable BigDecimal quantity
			) {

		CurrencyConversion currencyConversion = service.convert(from, to, quantity);
		
		logger.info("{}", currencyConversion);
		return ResponseEntity.ok(currencyConversion);
	}
}
