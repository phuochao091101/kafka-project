package com.example.kafka.controller;

import com.example.kafka.model.AccountDTO;
import com.example.kafka.model.MessageDTO;
import com.example.kafka.model.StatisticDTO;
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
    @Autowired
    KafkaTemplate<String,Object> kafkaTemplate;
    @PostMapping("/new")
    public AccountDTO create(@RequestBody AccountDTO accountDTO){
        StatisticDTO statisticDTO = new StatisticDTO(accountDTO.getEmail() + "" + accountDTO.getName(), new Date());
        MessageDTO messageDTO=new MessageDTO();
        messageDTO.setTo(accountDTO.getEmail());
        messageDTO.setToName(accountDTO.getName());
        messageDTO.setSubject("Welcome");
        messageDTO.setContent("KKK");
        for (int i = 0; i < 100; i++) {
            kafkaTemplate.send("notification",messageDTO);
        }
        kafkaTemplate.send("statistic",statisticDTO);
        return accountDTO;
    }
}
