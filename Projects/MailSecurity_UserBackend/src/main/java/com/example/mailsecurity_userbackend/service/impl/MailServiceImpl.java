package com.example.mailsecurity_userbackend.service.impl;

import com.example.mailsecurity_userbackend.model.Mail;
import com.example.mailsecurity_userbackend.service.MailService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
@Service
public class MailServiceImpl implements MailService {
    @Override
    public List<Mail> GetAllMails(String username, String password) {
        String url = "http://localhost:8080/api/v1/mail/" + username + "/" + password;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List> responseEntity = restTemplate.getForEntity(url, List.class);
        return (List<Mail>) responseEntity.getBody();
    }
}
