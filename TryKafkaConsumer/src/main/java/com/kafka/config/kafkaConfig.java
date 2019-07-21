package com.kafka.config;

import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.kafka.model.Sms;
@EnableKafka
@Configuration
public class kafkaConfig {
	
	@Bean
	public ConsumerFactory<String,Sms> consumerFactory(){
		Map<String,Object> configs=new HashMap<>();
		configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
		configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class);
		configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,JsonDeserializer.class);
		return new DefaultKafkaConsumerFactory<>(configs,new StringDeserializer(),new JsonDeserializer<>(Sms.class));
	}
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String,Sms> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String,Sms> factory=new ConcurrentKafkaListenerContainerFactory<String, Sms>();
		factory.setConsumerFactory(consumerFactory());
		return factory;
	}

}
