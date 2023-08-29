package com.example.consumerkafka.service;

import com.example.consumerkafka.model.MessageDTO;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;


import java.nio.charset.StandardCharsets;

@Service
public class EmailServiceImpl implements EmailService{
    Logger logger= LoggerFactory.getLogger(this.getClass());
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private SpringTemplateEngine templateEngine;
    @Value("${spring.mail.username}")
    private String from;
    @Override
    @Async
    public void sendEmail(MessageDTO messageDTO) {
        try {
            logger.info("START...Sending email");
            MimeMessage message=javaMailSender.createMimeMessage();
            MimeMessageHelper helper=new MimeMessageHelper(message, StandardCharsets.UTF_8.name());
            Context context=new Context();
            context.setVariable("name",messageDTO.getToName());
            context.setVariable("content",messageDTO.getContent());
            String html=templateEngine.process("welcome-email",context);

            helper.setTo(messageDTO.getTo());
            helper.setText(html,true);
            helper.setSubject(messageDTO.getSubject());
            helper.setFrom(from);
            javaMailSender.send(message);
            logger.info("Ending... Email");
        } catch (MessagingException e) {
            logger.error("Send mail Error "+e.getMessage());
        }
    }
}
