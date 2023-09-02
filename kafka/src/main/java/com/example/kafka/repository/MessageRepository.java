package com.example.kafka.repository;

import com.example.kafka.model.MessageDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<MessageDTO,Integer> {
    List<MessageDTO> findByStatus(Boolean status);
}
