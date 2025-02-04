package com.effigopracticeproject.learning_portal.entity;

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
    @Column(name = "favourite_id")
    private String favouriteId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "registration_id", nullable = false)
    private RegisteredCourses registeredCourses;
}
