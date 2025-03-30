package com.effigoproject.smart_inventory_system.repository;

import com.effigoproject.smart_inventory_system.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}