package com.effigopracticeproject.learning_portal.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "registered_courses")
public class RegisteredCourses {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "registration_id", nullable = false)
    private String registrationId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

}
