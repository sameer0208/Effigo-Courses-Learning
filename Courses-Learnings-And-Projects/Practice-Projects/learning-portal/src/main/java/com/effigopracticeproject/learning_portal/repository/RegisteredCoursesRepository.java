package com.effigopracticeproject.learning_portal.repository;

import com.effigopracticeproject.learning_portal.entity.RegisteredCourses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RegisteredCoursesRepository extends JpaRepository<RegisteredCourses, String> {

    @Query("SELECT rc FROM RegisteredCourses rc WHERE rc.user.userId = :userId AND rc.course.courseId = :courseId")
    List<RegisteredCourses> findByUserIdAndCourseId(@Param("userId") String userId, @Param("courseId") String courseId);
}
