package com.example1.integration;

import com.example1.service.CompareRatesService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CompareRatesServiceTest {
	@Autowired
	CompareRatesService sut;

	private final static String CURRENCY = "USD";
	private final static int RESULT = -1; // may put 1/0/-1 accordingly to daily currency rates changes

	@Test
	void compareRatesMethodTest() {
		int compareRatesServiceResult = sut.compareRates(CURRENCY);
		Assertions.assertEquals(RESULT,compareRatesServiceResult);
	}
}
