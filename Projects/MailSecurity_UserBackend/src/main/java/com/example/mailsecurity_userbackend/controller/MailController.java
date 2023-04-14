package com.example.mailsecurity_userbackend.controller;

import com.example.mailsecurity_userbackend.model.Client;
import com.example.mailsecurity_userbackend.model.Mail;
import com.example.mailsecurity_userbackend.repository.ClientRepository;
import com.example.mailsecurity_userbackend.service.CryptographyService;
import com.example.mailsecurity_userbackend.service.MailService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLEncoder;
import java.util.List;

@RestController
@RequestMapping("/api/v1/mail")
public class MailController {

    private final MailService mailService;
    private final CryptographyService cryptographyService;
    private final ClientRepository clientRepository;

    public MailController(MailService mailService, CryptographyService cryptographyService, ClientRepository clientRepository) {
        this.mailService = mailService;
        this.cryptographyService = cryptographyService;
        this.clientRepository = clientRepository;
    }


    @GetMapping("/{id}")
    public List<Mail> GetAllMails(@PathVariable Long id) throws Exception {
        Client client = clientRepository.findById(id).orElse(null);1
        String email = cryptographyService.Decrypt(client.getEmail());
        String password = cryptographyService.Decrypt(client.getPassword());
        return mailService.GetAllMails(URLEncoder.encode(email), URLEncoder.encode(password));
    }
}
