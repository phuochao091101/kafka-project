package com.example.kafka.repository;

import com.example.kafka.model.AccountDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountDTO,Integer> {
}
