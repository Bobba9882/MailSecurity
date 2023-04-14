package com.example.mailsecurity_userbackend.service.impl;

import com.example.mailsecurity_userbackend.service.CryptographyService;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class CryptographyServiceImpl implements CryptographyService {

    private static final String ALGORITHM = "AES";
    private static final byte[] KEY = "IL0V3ADR13".getBytes(StandardCharsets.UTF_8);


    @Override
    public String Encrypt(String plainText) throws Exception{
        Key key = generateKey();
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedValue = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encryptedValue);
    }

    @Override
    public String Decrypt(String encryptedText) throws Exception{
        Key key = generateKey();
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decodedValue = Base64.getDecoder().decode(encryptedText);
        byte[] decryptedValue = cipher.doFinal(decodedValue);
        return new String(decryptedValue, StandardCharsets.UTF_8);
    }

    private Key generateKey() throws Exception {
        MessageDigest sha = MessageDigest.getInstance("SHA-256");
        byte[] keyBytes = Arrays.copyOf(sha.digest(KEY), 16);
        return new SecretKeySpec(keyBytes, ALGORITHM);
    }


}
