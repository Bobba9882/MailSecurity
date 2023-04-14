package com.example.mailsecurity_userbackend.service;

public interface CryptographyService {
    public String encrypt(String plainText) throws Exception;
    public String decrypt(String encryptedText) throws Exception;
}
