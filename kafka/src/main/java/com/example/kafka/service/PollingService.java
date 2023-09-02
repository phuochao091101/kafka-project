package com.example.kafka.service;

import com.example.kafka.model.MessageDTO;
import com.example.kafka.model.StatisticDTO;
import com.example.kafka.repository.AccountRepository;
import com.example.kafka.repository.MessageRepository;
import com.example.kafka.repository.StatisticRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PollingService {
    Logger logger= LoggerFactory.getLogger(this.getClass());
    @Autowired
    KafkaTemplate<String,Object> kafkaTemplate;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    MessageRepository messageRepository;
    @Autowired
    StatisticRepository statisticRepository;

    @Scheduled(fixedDelay = 1000)
    public void producer(){
        List<MessageDTO> messageDTOS=messageRepository.findByStatus(false);
        List<StatisticDTO> statisticDTOS=statisticRepository.findByStatus(false);
        for (MessageDTO messageDTO: messageDTOS) {
            kafkaTemplate.send("notification",messageDTO).whenComplete((result,ex)->{
                if (ex == null) {
                    logger.info("Message sent successfully {}", result);
                    messageDTO.setStatus(true);
                    messageRepository.save(messageDTO);
                } else {
                    logger.error("Error sending message", ex);
                }
            });
        }
        for (StatisticDTO statisticDTO: statisticDTOS) {
            kafkaTemplate.send("statistic",statisticDTO).whenComplete((result,ex)->{
                if (ex == null) {
                    logger.info("Message sent successfully {}", result);
                    statisticDTO.setStatus(true);
                    statisticRepository.save(statisticDTO);
                } else {
                    logger.error("Error sending message", ex);
                }
            });
        }
    }
    @Scheduled(fixedDelay = 60000)
    public void delete(){
        List<MessageDTO> messageDTOS=messageRepository.findByStatus(true);
        List<StatisticDTO> statisticDTOS=statisticRepository.findByStatus(true);
        messageRepository.deleteAllInBatch(messageDTOS);
        statisticRepository.deleteAllInBatch(statisticDTOS);
    }
}
