package com.effigopracticeproject.learning_portal.repository;

import com.effigopracticeproject.learning_portal.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, String> {
}
