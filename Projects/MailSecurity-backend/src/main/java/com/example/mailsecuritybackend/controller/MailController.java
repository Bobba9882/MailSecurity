package com.example.mailsecuritybackend.controller;

import com.example.mailsecuritybackend.model.Mail;
import com.example.mailsecuritybackend.service.MailService;
import com.example.mailsecuritybackend.service.TextCensoringService;
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
    private final TextCensoringService textCensoringService;

    public MailController(MailService mailService, TextCensoringService textCensoringService) {
        this.mailService = mailService;
        this.textCensoringService = textCensoringService;
    }

    @GetMapping
    public List<Mail> GetAllMails() {
        List<Mail> mails = mailService.getAllMail();
        for (Mail mail: mails) {
            mail.body = textCensoringService.CensorSensitiveData(mail.body);
        }

        return mails;
    }
}
