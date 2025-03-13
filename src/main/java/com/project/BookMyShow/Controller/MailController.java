package com.project.BookMyShow.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MailController {

    @Autowired
    private JavaMailSender mailSender;

    @GetMapping("/send-mail")
    public void sendSimpleEmail() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("");
        message.setSubject("Testing mail");
        message.setText("Hello, How are you");
        message.setFrom(""); // Optional, but recommended
        mailSender.send(message);
    }

    @Configuration
    public static class Config {
    }
}
