package com.microservices.currencyexchangeservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.microservices.currencyexchangeservice.exception.CurrencyExchangeNotFound;
import com.microservices.currencyexchangeservice.model.ExchangeValue;
import com.microservices.currencyexchangeservice.repository.CurrencyExchangeRepository;

@Service
public class CurrencyExchangeService {
	
	@Autowired
	Environment env;

	@Autowired
	private CurrencyExchangeRepository repository;

	public ExchangeValue findCurrency(String from, String to) {
		ExchangeValue exchangeValue = repository.findByFromIgnoreCaseAndToIgnoreCase(from, to);
		
		if (exchangeValue == null) {
			throw new CurrencyExchangeNotFound(String.format("currency exchange not found from %s to %s", from, to));
		}
		
		exchangeValue.setPort(Integer.parseInt(env.getProperty("server.port")));
		return exchangeValue;
	}

}
