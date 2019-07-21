package com.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.kafka.model.Sms;

@SpringBootApplication
public class TryKafkaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TryKafkaApplication.class, args);
	}

}
