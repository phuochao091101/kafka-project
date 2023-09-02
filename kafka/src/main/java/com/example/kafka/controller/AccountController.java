package com.example.kafka.controller;

import com.example.kafka.model.AccountDTO;
import com.example.kafka.model.MessageDTO;
import com.example.kafka.model.StatisticDTO;
import com.example.kafka.repository.AccountRepository;
import com.example.kafka.repository.MessageRepository;
import com.example.kafka.repository.StatisticRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/account")
public class AccountController {
    Logger logger= LoggerFactory.getLogger(this.getClass());
    @Autowired
    KafkaTemplate<String,Object> kafkaTemplate;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    MessageRepository messageRepository;
    @Autowired
    StatisticRepository statisticRepository;
    @PostMapping("/new")
    public AccountDTO create(@RequestBody AccountDTO accountDTO){
        StatisticDTO statisticDTO = new StatisticDTO(1,accountDTO.getEmail() + "" + accountDTO.getName(), new Date(),false);
        MessageDTO messageDTO=new MessageDTO();
        messageDTO.setId(1);
        messageDTO.setTo(accountDTO.getEmail());
        messageDTO.setToName(accountDTO.getName());
        messageDTO.setSubject("Welcome");
        messageDTO.setContent("KKK");
        messageDTO.setStatus(false);
        accountRepository.save(accountDTO);
//        for (int i = 0; i < 20; i++) {
//            messageRepository.save(messageDTO);
//            statisticRepository.save(statisticDTO);
//        }
        messageRepository.save(messageDTO);
        statisticRepository.save(statisticDTO);
//        for (int i = 0; i < 100; i++) {
//            kafkaTemplate.send("notification",messageDTO).whenComplete((result,ex)->{
//                if (ex == null) {
//                    logger.info("Message sent successfully {}", result);
//                } else {
//                    logger.error("Error sending message", ex);
//                }
//            });
//        }
//        kafkaTemplate.send("statistic",statisticDTO);
        return accountDTO;
    }
}
