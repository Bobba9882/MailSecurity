package com.example.mailsecurity_userbackend;

import com.example.mailsecurity_userbackend.config.RsaKeyProperties;
import com.example.mailsecurity_userbackend.model.Client;
import com.example.mailsecurity_userbackend.model.User;
import com.example.mailsecurity_userbackend.repository.ClientRepository;
import com.example.mailsecurity_userbackend.repository.UserRepository;
import com.example.mailsecurity_userbackend.service.CryptographyService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableConfigurationProperties(RsaKeyProperties.class)
@SpringBootApplication
public class MailSecurityUserBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(MailSecurityUserBackendApplication.class, args);
    }

//    @Bean
//    CommandLineRunner commandLineRunner(UserRepository users, ClientRepository clients, PasswordEncoder passwordEncoder, CryptographyService cryptographyService) throws Exception {
//
//        Client client = new Client();
//        client.setEmail(cryptographyService.encrypt("adrietest25@outlook.com"));
//        client.setPassword(cryptographyService.encrypt("TEST25!?"));
//
//
//        User user = new User();
//        user.setUsername("karindebeste");
//        user.setPassword(passwordEncoder.encode("karindebeste")); ;
//        user.setClient(client);
//
//
//        return args -> {
//            clients.save(client);
//            users.save(user);
//        };
//    }

}
