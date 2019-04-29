package com.microservices.currencyconversionservice.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.currencyconversionservice.bean.CurrencyConversion;

@Service
public class CurrencyConversionService {

	@Autowired
	CurrencyExchangeServiceProxy currencyExchangeServiceProxy;

	public CurrencyConversion convert(String from, String to, BigDecimal quantity) {
		CurrencyConversion response = currencyExchangeServiceProxy.retrieveExchangeValue(from, to);

		return new CurrencyConversion(response.getId(), from, to, response.getConversionMultiple(), quantity,
				quantity.multiply(response.getConversionMultiple()), response.getPort());

	}

}
