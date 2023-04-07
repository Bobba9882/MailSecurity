package com.example.mailsecuritybackend.service.impl;

import com.example.mailsecuritybackend.model.Mail;
import com.example.mailsecuritybackend.service.MailService;
import org.springframework.stereotype.Service;

import java.util.*;


import java.util.ArrayList;
import java.util.List;
import javax.mail.*;
import javax.mail.search.FlagTerm;

@Service
public class MailServiceImpl implements MailService {

    @Override
    public List<Mail> getAllMail() {
        List<Mail> mails = new ArrayList<>();

        for (int i = 0; i < 10; i++){
            Mail mail = new Mail();
            mail.body = "TEST TEST TEST" + i;
            mail.title = "TITLE TEST " + i;
            mails.add(mail);
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


    public static List<Mail> check(String host,String user,
                             String password)
    {
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

            // retrieve the messages from the folder in an array and print it
            Message[] messages = emailFolder.getMessages();

            for (int i = 0, n = messages.length; i < n; i++) {
                Message message = messages[i];
                Mail mail = new Mail();
                mail.sender = Arrays.toString(message.getFrom());
                mail.title = message.getSubject();
                mail.body = message.getContent().toString();
                mails.add(mail);
            }

            //close the store and folder objects
            emailFolder.close(false);
            store.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mails;
    }


}
