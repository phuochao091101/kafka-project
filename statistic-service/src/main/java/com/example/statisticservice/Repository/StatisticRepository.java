package com.example.statisticservice.Repository;

import com.example.statisticservice.entity.Statistic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatisticRepository extends JpaRepository<Statistic,Integer> {
}
