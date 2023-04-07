package com.example.mailsecuritybackend.service.impl;

import com.example.mailsecuritybackend.model.Mail;
import com.example.mailsecuritybackend.service.MailService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class MailServiceImpl implements MailService {

    @Override
    public List<Mail> getAllMail() {
        List<Mail> mails = new ArrayList<>();

        for (int i = 0; i < 10; i++){
            Mail mail = new Mail();
            mail.body = "TEST TEST TEST" + i;
            mail.title = "TITLE TEST " + i;
            mails.add(mail);
        }

        return mails;
    }

    @Override
    public Mail getSingleMail(String title) {
        Mail mail = new Mail();
        mail.body = "TEST TEST TEST";
        mail.title = "TITLE TEST";
        return mail;
    }
}
