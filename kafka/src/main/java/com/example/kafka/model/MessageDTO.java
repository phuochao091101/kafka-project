package com.example.kafka.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
@Entity
public class MessageDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "to_email")
    private String to;
    private String toName;
    private String subject;
    private String content;
    private boolean status;
}
