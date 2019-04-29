package com.microservices.currencyexchangeservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.currencyexchangeservice.exception.CurrencyExchangeNotFound;
import com.microservices.currencyexchangeservice.model.ExchangeValue;
import com.microservices.currencyexchangeservice.repository.CurrencyExchangeRepository;

@Service
public class CurrencyExchangeService {

	@Autowired
	private CurrencyExchangeRepository repository;

	public ExchangeValue findCurrency(String from, String to) {
		ExchangeValue exchangeValue = repository.findByFromAndTo(from.toUpperCase(), to.toUpperCase());
		
		if (exchangeValue == null) {
			throw new CurrencyExchangeNotFound(String.format("currency exchange not found from %s to %s", from, to));
		}
		
		return exchangeValue;
	}

}
