package com.example.mailsecurity_userbackend;

import com.example.mailsecurity_userbackend.config.RsaKeyProperties;
import com.example.mailsecurity_userbackend.model.Client;
import com.example.mailsecurity_userbackend.model.User;
import com.example.mailsecurity_userbackend.repository.ClientRepository;
import com.example.mailsecurity_userbackend.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@EnableConfigurationProperties(RsaKeyProperties.class)
@SpringBootApplication
public class MailSecurityUserBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(MailSecurityUserBackendApplication.class, args);
    }

//    @Bean
//    CommandLineRunner commandLineRunner(UserRepository users, ClientRepository clients) {
//
//        Client client = new Client();
//        client.setEmail("mail@mail.nl");
//        client.setPassword("password");
//
//
//        User user = new User();
//        user.setUserName("HALLO");
//        user.setPassword("HALLO");
//        user.setClient(client);
//
//
//        return args -> {
//            clients.save(client);
//            users.save(user);
//        };
//    }

}
