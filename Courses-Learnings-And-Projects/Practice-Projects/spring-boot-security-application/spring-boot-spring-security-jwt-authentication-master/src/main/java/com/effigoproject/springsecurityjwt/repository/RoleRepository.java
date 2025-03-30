package com.effigoproject.springsecurityjwt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.effigoproject.springsecurityjwt.entity.ERole;
import com.effigoproject.springsecurityjwt.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
}
