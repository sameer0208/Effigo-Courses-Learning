package com.effigopracticeproject.learning_portal.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

//@Data
//@NoArgsConstructor
//@AllArgsConstructor
@Entity
@Table(name ="users")
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
    private String userName;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<RegisteredCourses> registeredCourses;

    public User() {
    }

    public User(String userId, String password, LocalDateTime registrationDateTime, String userRole, String userName, List<RegisteredCourses> registeredCourses) {
        this.userId = userId;
        this.password = password;
        this.registrationDateTime = registrationDateTime;
        this.userRole = userRole;
        this.userName = userName;
        this.registeredCourses = registeredCourses;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getRegistrationDateTime() {
        return registrationDateTime;
    }

    public void setRegistrationDateTime(LocalDateTime registrationDateTime) {
        this.registrationDateTime = registrationDateTime;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<RegisteredCourses> getRegisteredCourses() {
        return registeredCourses;
    }

    public void setRegisteredCourses(List<RegisteredCourses> registeredCourses) {
        this.registeredCourses = registeredCourses;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", registrationDateTime=" + registrationDateTime +
                ", userRole='" + userRole + '\'' +
                ", userName='" + userName + '\'' +
                ", registeredCourses=" + registeredCourses +
                '}';
    }
}
