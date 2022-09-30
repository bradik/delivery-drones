package com.example.deliverydrones;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class DeliveryDronesApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeliveryDronesApplication.class, args);
	}

}
