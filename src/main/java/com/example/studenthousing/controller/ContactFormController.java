package com.example.studenthousing.controller;

import com.example.studenthousing.ContactForm;
import com.example.studenthousing.EmailSender;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.mail.MessagingException;

@RestController
@RequestMapping("/api")
public class ContactFormController {

    private final EmailSender emailSender;

    public ContactFormController(EmailSender emailSender) {
        this.emailSender = emailSender;
    }

    @PostMapping("/contact")
    public ResponseEntity<String> submitContactForm(@RequestBody ContactForm contactForm) {
        try {
            String recipientEmail = "codegorilla9@gmail.com";  // Replace with your desired recipient email address
            String subject = "New Contact Form Submission";
            String body = "Name: " + contactForm.getName() + "\n"
                    + "Email: " + contactForm.getEmail() + "\n"
                    + "Message: " + contactForm.getMessage();

            emailSender.sendEmail(recipientEmail, subject, body);

            return ResponseEntity.ok("Contact form submitted successfully!");
        } catch (MessagingException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to submit contact form. Please try again later.");
        }
    }
}
