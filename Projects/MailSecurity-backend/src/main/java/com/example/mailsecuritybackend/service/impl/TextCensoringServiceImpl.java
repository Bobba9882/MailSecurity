package com.example.mailsecuritybackend.service.impl;

import com.example.mailsecuritybackend.service.TextCensoringService;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class TextCensoringServiceImpl implements TextCensoringService {

    private final Pattern phonePattern = Pattern.compile("(?:0|(?:\\+|00) ?31 ?)(?:[1-9] ?(?:[0-9] ?){8}|6 ?-? ?[1-9] ?(?:[0-9] ?){7}|[1,2345789]\\d ?-? ?[1-9] ?(?:[0-9] ?){6}|[1,2345789]\\d{2} ?-? ?[1-9] ?(?:[0-9] ?){5})");

    private final Pattern ibanPattern = Pattern.compile("NL[0-9]{2} ?[A-Z]{4} ?[0-9]{4} ?[0-9]{4} ?[0-9]{2}");

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
