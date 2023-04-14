package com.example.mailsecuritybackend.service.impl;

import com.example.mailsecuritybackend.model.Mail;
import com.example.mailsecuritybackend.service.MailService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;


import java.util.ArrayList;
import java.util.List;
import javax.mail.*;
import javax.mail.internet.MimeMultipart;
import javax.mail.search.FlagTerm;

@Service
public class MailServiceImpl implements MailService {

    @Override
    public List<Mail> getAllMail() {
        // Set up email account credentials
        String host = "outlook.office365.com";
        String username = "adrietest25@outlook.com";
        String password = "TEST25!?";
        List<Mail> mails = new ArrayList<>();
        try {
            // Set up email properties
            Properties emailProperties = new Properties();
            emailProperties.put("mail.pop3.host", host);
            emailProperties.put("mail.pop3.port", "995");
            emailProperties.put("mail.pop3.starttls.enable", "true");

            // Create email session
            Session emailSession = Session.getDefaultInstance(emailProperties);

            // Connect to email server using POP3 protocol
            Store emailStore = emailSession.getStore("pop3s");
            emailStore.connect(host, username, password);

            // Open INBOX folder and retrieve email messages
            Folder inboxFolder = emailStore.getFolder("INBOX");
            inboxFolder.open(Folder.READ_ONLY);
            Message[] messages = inboxFolder.getMessages();

            int mailId = 0;
            // Iterate through messages and create Mail objects for each
            for (Message message : messages) {
                Mail mail = new Mail();
                mail.id = mailId;
                mail.sender = Arrays.toString(message.getFrom());
                mail.receiver = Arrays.toString(message.getAllRecipients());
                mail.title = message.getSubject();
                mail.body = getTextFromMessage(message);
                mail.date = message.getSentDate();
                mails.add(mail);
                mailId++;
            }

            // Close folder and store
            inboxFolder.close(false);
            emailStore.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mails;
    }

    // Extracts text from a given message object
    private String getTextFromMessage(Message message) throws MessagingException, IOException {
        // If message is plain text
        if (message.isMimeType("text/plain")) {
            return message.getContent().toString();
        }
        // If message is multipart
        if (message.isMimeType("multipart/*")) {
            MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
            return getTextFromMimeMultipart(mimeMultipart);
        }
        // Otherwise return an empty string
        return "";
    }

    // Extracts text from a given multipart object
    private String getTextFromMimeMultipart(MimeMultipart mimeMultipart) throws MessagingException, IOException {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < mimeMultipart.getCount(); i++) {
            BodyPart bodyPart = mimeMultipart.getBodyPart(i);
            // If body part is plain text
            if (bodyPart.isMimeType("text/plain")) {
                // Append the text to result and return
                result.append(bodyPart.getContent());
                return result.toString();
            }
            // Append the parsed body part to result
            result.append(parseBodyPart(bodyPart));
        }
        // Return the result
        return result.toString();
    }

    // Parses a given body part and returns its plain text content
    private String parseBodyPart(BodyPart bodyPart) throws MessagingException, IOException {
        // If body part is HTML
        if (bodyPart.isMimeType("text/html")) {
        // Parse the HTML and extract the plain text
            return org.jsoup.Jsoup.parse(bodyPart.getContent().toString()).text();
        }
        // If body part is multipart, extract its plain text content
        if (bodyPart.getContent() instanceof MimeMultipart) {
            return getTextFromMimeMultipart((MimeMultipart) bodyPart.getContent());
        }
        // Otherwise return an empty string
        return "";
    }


}
