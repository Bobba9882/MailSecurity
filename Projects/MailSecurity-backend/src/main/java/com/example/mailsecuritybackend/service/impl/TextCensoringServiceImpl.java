package com.example.mailsecuritybackend.service.impl;

import com.example.mailsecuritybackend.service.TextCensoringService;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class TextCensoringServiceImpl implements TextCensoringService {

    private final Pattern phonePattern = Pattern.compile("[+]?[(]?[0-9]{3}[)]?[-\\s.]?[0-9]{3}[-\\s.]?[0-9]{4,6}");

    private final Pattern ibanPattern = Pattern.compile("\\b[A-Z]{2}[0-9]{2}[A-Z]{4}[0-9]{10}\\b");

    @Override
    public String CensorSensitiveData(String content) {

        // Replace IBAN numbers with "*****"
        Matcher ibanMatcher = ibanPattern.matcher(content);
        content = ibanMatcher.replaceAll("[REDACTED_IBAN]");


        // Replace phone numbers with "*******"
        Matcher phoneMatcher = phonePattern.matcher(content);
        content = phoneMatcher.replaceAll("[REDACTED_PHONE]");

        return content;
    }
}
