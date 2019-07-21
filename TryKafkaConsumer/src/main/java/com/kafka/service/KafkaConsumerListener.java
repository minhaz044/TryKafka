package com.kafka.service;

import org.springframework.stereotype.Service;
import com.kafka.model.Sms;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

@Service
public class KafkaConsumerListener {
	@Autowired
	private SmsService smsService;
	@Autowired
	private xmlsService xmlsService;
	@Autowired
	private ExtractSms extractSms;
	
	@KafkaListener(topics="SMS",groupId="db_group")
	public void DBOperator(Sms sms) {
		smsService.saveSms(sms);
		//System.out.println("+++From DBOperator"+sms.getMessage());
	}
	@KafkaListener(topics="SMS",groupId="xlsx_group")
	public void XmlsGenarator(Sms sms) {
		List<Sms> smsList=new ArrayList<>();
		smsList.add(sms);
		xmlsService.generateExcell(smsList);
		//System.out.println("***From Xmls Generator"+sms.getMessage());
	}
	@KafkaListener(topics="SMS",groupId="pattern_group")
	public void patternGenarator(Sms sms) {
		//System.out.println("***From Pattern Generator"+sms.getMessage());
	}
}
