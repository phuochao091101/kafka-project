package com.example.consumerkafka.service;

import com.example.consumerkafka.model.MessageDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    Logger logger= LoggerFactory.getLogger(this.getClass());
    @Autowired
    private EmailService emailService;
    @KafkaListener(id="notificationGroup",topics = "notification")
    public void listen(MessageDTO messageDTO){
        logger.info("Received: ",messageDTO.getTo());
        emailService.sendEmail(messageDTO);
    }
}
