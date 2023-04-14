package com.example.mailsecurity_userbackend.repository;

import com.example.mailsecurity_userbackend.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
