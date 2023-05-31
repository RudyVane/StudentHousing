package com.example.studenthousing.services;

import com.example.studenthousing.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
public class EmailService {

    private final EmailSender emailSender;

    @Autowired
    public EmailService(EmailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void sendMyEmail() {
        String recipientEmail = "recipient@example.com";
        String subject = "Hello";
        String body = "This is the email body.";

        try {
            emailSender.sendEmail(recipientEmail, subject, body);
            System.out.println("Email sent successfully.");
        } catch (MessagingException e) {
            System.out.println("Failed to send email: " + e.getMessage());
        }
    }
}
