package com.example.sameerkart.repository;

import com.example.sameerkart.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class UserRepository extends JpaRepository<User, String> {
}
