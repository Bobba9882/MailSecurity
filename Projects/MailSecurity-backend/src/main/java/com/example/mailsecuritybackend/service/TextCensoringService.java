package com.example.mailsecuritybackend.service;

public interface TextCensoringService {
    String CensorSensitiveData(String content);
}
