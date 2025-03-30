package com.effigopracticeproject.learning_portal.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "favourite_course")
public class FavouriteCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "favourite_id", nullable = false)
    private String favouriteId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    @JoinColumn(name = "registration_id", nullable = false)
    private RegisteredCourses registeredCourses;
}