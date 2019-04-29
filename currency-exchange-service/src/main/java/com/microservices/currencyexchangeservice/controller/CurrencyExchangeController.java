package com.microservices.currencyexchangeservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.currencyexchangeservice.model.ExchangeValue;
import com.microservices.currencyexchangeservice.service.CurrencyExchangeService;

@RestController
public class CurrencyExchangeController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	Environment env;
	
	@Autowired
	private CurrencyExchangeService service;
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public ResponseEntity<ExchangeValue> retrieveExchangeValue(@PathVariable String from, 
			@PathVariable String to) {
		
		ExchangeValue exchangeValue = service.findCurrency(from, to);
		exchangeValue.setPort(Integer.parseInt(env.getProperty("local.server.port")));
		
		logger.info("{}", exchangeValue);
		return ResponseEntity.ok(exchangeValue);
	}

}
