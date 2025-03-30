package com.effigopracticeproject.learning_portal.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne( fetch = FetchType.EAGER)
    @JsonBackReference
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @OneToMany(mappedBy = "registeredCourses", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<FavouriteCourse> favouriteCourses;

}
