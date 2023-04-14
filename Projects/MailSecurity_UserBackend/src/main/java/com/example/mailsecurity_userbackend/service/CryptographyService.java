package com.example.mailsecurity_userbackend.service;

public interface CryptographyService {
    public String Encrypt(String plainText) throws Exception;
    public String Decrypt(String encryptedText) throws Exception;
}
