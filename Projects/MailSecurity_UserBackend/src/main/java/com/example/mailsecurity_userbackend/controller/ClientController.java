package com.example.mailsecurity_userbackend.controller;

import com.example.mailsecurity_userbackend.model.Client;
import com.example.mailsecurity_userbackend.repository.ClientRepository;
import com.example.mailsecurity_userbackend.service.CryptographyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/client")
public class ClientController {

    private final ClientRepository clientRepository;
    private final CryptographyService cryptographyService;

    public ClientController(ClientRepository clientRepository, CryptographyService cryptographyService) {
        this.clientRepository = clientRepository;
        this.cryptographyService = cryptographyService;
    }

    @GetMapping("/{id}")
    public String client(@PathVariable Long id) throws Exception {
        return cryptographyService.decrypt(clientRepository.findById(id).get().getEmail()) ;
    }
}
