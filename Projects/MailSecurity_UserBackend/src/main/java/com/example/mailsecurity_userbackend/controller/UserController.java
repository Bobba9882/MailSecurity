package com.example.mailsecurity_userbackend.controller;

import com.example.mailsecurity_userbackend.model.Client;
import com.example.mailsecurity_userbackend.repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/user")
@RestController
public class UserController {
    private final UserRepository users;

    public UserController(UserRepository users) {
        this.users = users;
    }

    @GetMapping
    public Client getClientInfo(Long userId) {
        return users.findById(userId).get().client;
    }

}
