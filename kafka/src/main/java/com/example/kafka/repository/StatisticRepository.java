package com.example.kafka.repository;

import com.example.kafka.model.MessageDTO;
import com.example.kafka.model.StatisticDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StatisticRepository extends JpaRepository<StatisticDTO,Integer> {
    List<StatisticDTO> findByStatus(Boolean status);
}
