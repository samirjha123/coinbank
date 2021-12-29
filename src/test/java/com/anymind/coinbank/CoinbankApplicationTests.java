package com.anymind.coinbank;

import com.anymind.coinbank.controller.CoinController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest
class CoinbankApplicationTests {

	@Autowired
	private CoinController controller;

	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();
	}

}
