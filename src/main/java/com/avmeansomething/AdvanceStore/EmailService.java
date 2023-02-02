package com.avmeansomething.AdvanceStore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    public void sendSimpleMessage(
            String subject, String text, String mailTo) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("ivaida2001@gmail.com");
        message.setTo(mailTo);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }
}