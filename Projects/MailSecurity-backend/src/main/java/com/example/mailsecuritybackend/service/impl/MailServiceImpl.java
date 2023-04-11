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
        String host = "outlook.office365.com";
        String user = "adrietest25@outlook.com";
        String password = "TEST25!?";

        List<Mail> mails = new ArrayList<>();
        try {

            //create properties field
            Properties properties = new Properties();

            properties.put("mail.pop3.host", host);
            properties.put("mail.pop3.port", "995");
            properties.put("mail.pop3.starttls.enable", "true");
            Session emailSession = Session.getDefaultInstance(properties);

            //create the POP3 store object and connect with the pop server
            Store store = emailSession.getStore("pop3s");

            store.connect(host, user, password);

            //create the folder object and open it
            Folder emailFolder = store.getFolder("INBOX");
            emailFolder.open(Folder.READ_ONLY);

            int id = 0;
            // retrieve the messages from the folder in an array and print it
            Message[] messages = emailFolder.getMessages();

            for (Message message : messages) {
                Mail mail = new Mail();
                mail.id = id;
                mail.sender = Arrays.toString(message.getFrom());
                mail.title = message.getSubject();
                mail.body = getTextFromMessage(message);
                mails.add(mail);
                id++;
            }

            //close the store and folder objects
            emailFolder.close(false);
            store.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mails;
    }

    @Override
    public Mail getSingleMail(String title) {
        Mail mail = new Mail();
        mail.body = "TEST TEST TEST";
        mail.title = "TITLE TEST";
        return mail;
    }

    private String getTextFromMessage(Message message) throws MessagingException, IOException {
        if (message.isMimeType("text/plain")) {
            return message.getContent().toString();
        }
        if (message.isMimeType("multipart/*")) {
            MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
            return getTextFromMimeMultipart(mimeMultipart);
        }
        return "";
    }

    private String getTextFromMimeMultipart(
            MimeMultipart mimeMultipart)  throws MessagingException, IOException{
        String result = "";
        for (int i = 0; i < mimeMultipart.getCount(); i++) {
            BodyPart bodyPart = mimeMultipart.getBodyPart(i);
            if (bodyPart.isMimeType("text/plain")) {
                return result + bodyPart.getContent(); // without return, same text appears twice in my tests
            }
            result += this.parseBodyPart(bodyPart);
        }
        return result;
    }

    private String parseBodyPart(BodyPart bodyPart) throws MessagingException, IOException {
        if (bodyPart.isMimeType("text/html")) {
            return  org.jsoup.Jsoup
                    .parse(bodyPart.getContent().toString())
                    .text();
        }
        if (bodyPart.getContent() instanceof MimeMultipart){
            return getTextFromMimeMultipart((MimeMultipart)bodyPart.getContent());
        }

        return "";
    }


}
