package com.example.mailsecuritybackend.controller;

import com.example.mailsecuritybackend.model.Mail;
import com.example.mailsecuritybackend.service.MailService;
import com.example.mailsecuritybackend.service.impl.MailServiceImpl;
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
        String host = "outlook.office365.com";
        String username = "adrietest25@outlook.com";
        String password = "TEST25!?";

        MailServiceImpl test = new MailServiceImpl();

        return test.check(host, username, password);
    }
}
