package com.example.statisticservice.service;

import com.example.statisticservice.Repository.StatisticRepository;
import com.example.statisticservice.entity.Statistic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class StatisticService {
    Logger logger= LoggerFactory.getLogger(this.getClass());
    @Autowired
    private StatisticRepository statisticRepository;
    @KafkaListener(id="statisticGroup",topics = "statistic")
    public void listen(Statistic statistic){
        logger.info("Received: ",statistic.getMessage());
        statisticRepository.save(statistic);
    }
}
