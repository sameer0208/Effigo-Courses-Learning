package com.effigopracticeproject.learning_portal.repository;

import com.effigopracticeproject.learning_portal.entity.FavouriteCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavouriteCourseRepository extends JpaRepository<FavouriteCourse, String> {
}
