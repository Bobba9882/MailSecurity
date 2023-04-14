package com.example.mailsecuritybackend.controller;

import com.example.mailsecuritybackend.model.Mail;
import com.example.mailsecuritybackend.service.MailService;
import com.example.mailsecuritybackend.service.TextCensoringService;
import org.springframework.web.bind.annotation.*;

import java.io.Console;
import java.net.URLDecoder;
import java.util.List;
import java.util.logging.Logger;

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

    @GetMapping("/{username}/{password}")
    public List<Mail> GetAllMails(@PathVariable String username, @PathVariable String password) {
        username= URLDecoder.decode(username);
        password= URLDecoder.decode(password);
        List<Mail> mails = mailService.getAllMail(username, password);
        for (Mail mail: mails) {
            mail.body = textCensoringService.CensorSensitiveData(mail.body);
        }

        return mails;
    }
}
