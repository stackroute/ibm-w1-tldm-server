package com.stackroute.tldm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MessagePersistenceServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MessagePersistenceServiceApplication.class, args);
	}
}
