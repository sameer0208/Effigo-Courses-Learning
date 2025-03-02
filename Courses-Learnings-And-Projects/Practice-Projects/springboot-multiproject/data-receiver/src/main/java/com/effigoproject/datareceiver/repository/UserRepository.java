package com.effigoproject.datareceiver.repository;

import com.effigoproject.datareceiver.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
