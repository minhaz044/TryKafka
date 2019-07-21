package com.kafka.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.kafka.model.Sms;

public interface SmsRepository extends JpaRepository<Sms,Integer>{

}
