package com.microservices.currencyconversionservice.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.microservices.currencyconversionservice.bean.CurrencyConversion;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CurrencyConversionServiceTest {
	
	@Autowired
	private CurrencyConversionService service;
	
	@Test
	public void getCurrencyConversionFromUSDtoBR() {
		CurrencyConversion currencyConversion = service.convert("USD", "BR", BigDecimal.valueOf(1000));
		
		assertEquals("USD", currencyConversion.getFrom());
		assertEquals("BR", currencyConversion.getTo());
		assertThat(currencyConversion.getTotalCalculatedAmount().intValue(), is(greaterThan(0)));
	}
	
	@Test
	public void getCurrencyConversionFromUSDtoBRCaseSensitive() {
		CurrencyConversion currencyConversion = service.convert("usd", "br", BigDecimal.valueOf(1000));
		
		assertEquals("USD", currencyConversion.getFrom());
		assertEquals("BR", currencyConversion.getTo());
		assertThat(currencyConversion.getTotalCalculatedAmount().intValue(), is(greaterThan(0)));
	}
	
	@Test(expected=Exception.class)
	public void getNoExistentConversion() {
		service.convert("US", "B", BigDecimal.valueOf(1000));
	}

}
