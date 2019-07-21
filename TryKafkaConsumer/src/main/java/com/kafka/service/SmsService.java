package com.kafka.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kafka.dao.SmsRepository;
import com.kafka.model.Sms;
@Service
public class SmsService {
	@Autowired
	private SmsRepository smsRepo;
	public List<Sms> getAllSms(){
		return smsRepo.findAll();
	}
	public Sms saveSms(Sms sms){
		return smsRepo.save(sms);
	}

}
