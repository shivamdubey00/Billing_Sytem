package com.example.scms;

import com.example.scms.EmailRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/email")
@CrossOrigin

public class EmailController {
    @Autowired
    private JavaMailSender mailSender;
    @PostMapping("/send")
    public String sendEmail(@RequestBody EmailRequest request) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("codolog.info@gmail.com");
            message.setTo(request.getTo());
            message.setSubject("Static Subject");
            message.setText("Hello,\n\nThis is a static email message.\n\nBest regards,\nSpring Boot Team");

            mailSender.send(message);
            return "Email sent successfully to " + request.getTo();
        } catch (Exception e) {
            return "Failed to send email: " + e.getMessage();
        }
    }
}
