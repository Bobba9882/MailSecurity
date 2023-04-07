package com.example.mailsecuritybackend.controller;

import com.example.mailsecuritybackend.model.Mail;
import com.example.mailsecuritybackend.service.MailService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/mail")
@CrossOrigin
public class MailController {
    private final MailService mailService;

    public MailController(MailService mailService) {
        this.mailService = mailService;
    }

    @GetMapping
    public List<Mail> GetAllMails() {
        return mailService.getAllMail();
    }
}
