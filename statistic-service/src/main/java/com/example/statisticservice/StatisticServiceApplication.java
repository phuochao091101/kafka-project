package com.example.statisticservice;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaOperations;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.support.converter.JsonMessageConverter;
import org.springframework.util.backoff.FixedBackOff;

@SpringBootApplication
public class StatisticServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StatisticServiceApplication.class, args);
	}
	@Bean
	JsonMessageConverter converter(){
		return new JsonMessageConverter();
	}
//	@Bean
//	NewTopic dlt(){
//		return new NewTopic("statistic.DLT",1,(short) 1);
//	}
	@Bean
	DefaultErrorHandler errorHandler(KafkaOperations<String,Object> template){
		return new DefaultErrorHandler(new DeadLetterPublishingRecoverer(template), new FixedBackOff(1000L,2));
	}
}
