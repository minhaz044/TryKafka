package com.kafka.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.kafka.model.Sms;

@RestController
@RequestMapping("/publish")
public class SmsController {
	@Autowired
	private KafkaTemplate<String,Sms> kafkaTemplate;
	private final static String topic_1="SMS";
	

	@PostMapping("/sms")
	protected List<Sms> reciveSms(@RequestBody List<Sms> smsList ) {
		smsList.forEach(sms->{
			System.out.println(sms.getMessage());
			kafkaTemplate.send(topic_1,sms);
			});
		return smsList;
	}
	@GetMapping("/sms/{message}")
	protected String reciveSms(@PathVariable(name="message") String message) {
		return message;
	}

}
