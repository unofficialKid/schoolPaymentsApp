package com.schoolpayments.maintenance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class PaymentsTrackApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentsTrackApplication.class, args);
	}

}