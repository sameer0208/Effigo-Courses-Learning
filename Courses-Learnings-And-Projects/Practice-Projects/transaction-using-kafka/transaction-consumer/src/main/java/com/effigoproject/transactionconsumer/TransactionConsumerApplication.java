package com.effigoproject.transactionconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class TransactionConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransactionConsumerApplication.class, args);
	}

}
