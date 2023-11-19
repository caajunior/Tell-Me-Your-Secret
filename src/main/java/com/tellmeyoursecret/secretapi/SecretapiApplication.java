package com.tellmeyoursecret.secretapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SecretapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecretapiApplication.class, args);
	}

}
