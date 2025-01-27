package com.effigopracticeproject.learning_portal.repository;

import com.effigopracticeproject.learning_portal.entity.RegisteredCourses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisteredCoursesRepository extends JpaRepository<RegisteredCourses, String> {
}
