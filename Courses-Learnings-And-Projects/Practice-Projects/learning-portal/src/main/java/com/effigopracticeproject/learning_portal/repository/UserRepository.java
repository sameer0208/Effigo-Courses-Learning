package com.effigopracticeproject.learning_portal.repository;

import com.effigopracticeproject.learning_portal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
