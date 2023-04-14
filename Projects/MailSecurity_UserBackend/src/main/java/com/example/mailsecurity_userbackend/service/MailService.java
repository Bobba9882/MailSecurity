package com.example.mailsecurity_userbackend.service;

import com.example.mailsecurity_userbackend.model.Mail;

import java.util.List;

public interface MailService {
    List<Mail> GetAllMails(String username, String password);
}
