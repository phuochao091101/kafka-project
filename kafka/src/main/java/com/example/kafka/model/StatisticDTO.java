package com.example.kafka.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class StatisticDTO {
    private String message;
    private Date createdDate;
}
