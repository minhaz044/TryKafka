package com.kafka.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
@Service
public class CronService {
	//@Scheduled(cron = "0 15 10 15 * ?")
	//@Scheduled(cron="0 0 0 * * ?")
	//@Scheduled(fixedRate = 2000, initialDelay = 1000)
	public void downloadxlsx() {
		System.out.println("This Is our Cron Job");
		
	}

}
