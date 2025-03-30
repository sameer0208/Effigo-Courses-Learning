package com.effigoproject.spring_batch.repository;

import com.effigoproject.spring_batch.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users,String> {
}
