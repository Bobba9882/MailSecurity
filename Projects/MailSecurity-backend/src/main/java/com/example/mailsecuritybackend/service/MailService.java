package com.example.mailsecuritybackend.service;

import com.example.mailsecuritybackend.model.Mail;

import java.util.List;

public interface MailService {
    List<Mail> getAllMail(String username, String password);
}
