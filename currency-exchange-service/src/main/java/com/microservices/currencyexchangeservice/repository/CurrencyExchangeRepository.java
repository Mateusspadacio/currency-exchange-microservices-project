package com.microservices.currencyexchangeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservices.currencyexchangeservice.model.ExchangeValue;

@Repository
public interface CurrencyExchangeRepository extends JpaRepository<ExchangeValue, Long>{

	public ExchangeValue findByFromIgnoreCaseAndToIgnoreCase(String from, String to);
}
