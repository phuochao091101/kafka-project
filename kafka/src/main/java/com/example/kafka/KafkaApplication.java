package com.example.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class KafkaApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaApplication.class, args);
    }
    @Bean
    NewTopic notification(){
        return new NewTopic("notification",2,(short) 1);
    }
    @Bean
    NewTopic statistic(){
        return new NewTopic("statistic",1,(short) 1);
    }

}
