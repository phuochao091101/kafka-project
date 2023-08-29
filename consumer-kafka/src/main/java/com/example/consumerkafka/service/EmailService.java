package com.example.consumerkafka.service;

import com.example.consumerkafka.model.MessageDTO;

public interface EmailService {
    public void sendEmail(MessageDTO messageDTO);
}
