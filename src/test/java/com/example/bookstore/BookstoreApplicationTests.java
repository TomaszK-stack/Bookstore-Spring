package com.example.bookstore;

import com.example.bookstore.config.PaypalConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BookstoreApplicationTests {
	@Autowired
	PaypalConfig p;

	@Test
	void contextLoads() {
		System.out.printf(p.getBaseUrl());
	}

}
