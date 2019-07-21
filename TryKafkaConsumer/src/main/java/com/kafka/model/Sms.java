package com.kafka.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="m_sms")
public class Sms {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String smsPhoneNumber;
	private String message;
}