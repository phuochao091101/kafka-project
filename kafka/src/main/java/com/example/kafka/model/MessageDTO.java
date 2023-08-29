package com.example.kafka.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class MessageDTO {
    private String to;
    private String toName;
    private String subject;
    private String content;
}
