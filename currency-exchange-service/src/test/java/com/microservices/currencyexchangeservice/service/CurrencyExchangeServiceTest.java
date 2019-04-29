package com.microservices.currencyexchangeservice.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.microservices.currencyexchangeservice.exception.CurrencyExchangeNotFound;
import com.microservices.currencyexchangeservice.model.ExchangeValue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CurrencyExchangeServiceTest {

	@Autowired
	private CurrencyExchangeService service;
	
	@Test
	public void getExchangeValueUSDtoBR() {
		ExchangeValue exchangeValue = service.findCurrency("USD", "BR");
		
		assertNotNull(exchangeValue);
		assertNotNull(exchangeValue.getConversionMultiple());
		assertNotEquals(0, exchangeValue.getPort());
		assertEquals("USD", exchangeValue.getFrom());
		assertEquals("BR", exchangeValue.getTo());
	}
	
	@Test
	public void getExchangeValueUSDtoBRCaseSensitive() {
		ExchangeValue exchangeValue = service.findCurrency("usd", "br");
		
		assertNotNull(exchangeValue);
		assertNotNull(exchangeValue.getConversionMultiple());
		assertNotEquals(0, exchangeValue.getPort());
		assertEquals("USD", exchangeValue.getFrom());
		assertEquals("BR", exchangeValue.getTo());
	}
	
	@Test(expected=CurrencyExchangeNotFound.class)
	public void getNoExistentExchangeValue() {
		service.findCurrency("USD", "B");
	}
	
	@Test(expected=CurrencyExchangeNotFound.class)
	public void getNoExistentExchangeValueWithNullValues() {
		service.findCurrency(null, null);
	}
	
	@Test
	public void verifyThrowMessage() {
		try {
			service.findCurrency("USD", "B");
		} catch (CurrencyExchangeNotFound e) {
			assertEquals("currency exchange not found from USD to B", e.getMessage());
		}
	}

}
