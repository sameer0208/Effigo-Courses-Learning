package com.effigopracticeproject.learning_portal.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "registration_date_time", nullable = false)
    private LocalDateTime registrationDateTime;

    @Column(name = "user_role", nullable = false)
    private String userRole;

    @Column(name = "username", nullable = false)
    private String username;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference
    private List<RegisteredCourses> registeredCourses;
}