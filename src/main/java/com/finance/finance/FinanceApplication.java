package com.finance.finance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.finance.finance.infrastructure.config.JwtProperties;


@SpringBootApplication
@EnableConfigurationProperties(JwtProperties.class)
public class FinanceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinanceApplication.class, args);
	}

}
