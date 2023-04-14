package com.example.mailsecurity_userbackend.repository;

import com.example.mailsecurity_userbackend.model.Client;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<Client, Long> {
}
