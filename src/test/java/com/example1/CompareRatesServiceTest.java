package com.example1;

import com.example1.service.CompareRatesService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CompareRatesServiceTest {
	@Autowired
	CompareRatesService compareRatesService;

	private final static String CURRENCY = "RUB";
	private final static int RESULT = -1;

	@Test
	void compareRatesTest() {
		int compareRatesServiceResult = compareRatesService.compareRates(CURRENCY);
		Assertions.assertEquals(RESULT,compareRatesServiceResult);
	}
}
