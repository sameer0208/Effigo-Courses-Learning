package com.effigopracticeproject.learning_portal.controller;

import com.effigopracticeproject.learning_portal.dto.CourseRequestDto;
import com.effigopracticeproject.learning_portal.dto.CourseResponseDto;
import com.effigopracticeproject.learning_portal.entity.Course;
import com.effigopracticeproject.learning_portal.entity.RegisteredCourses;
import com.effigopracticeproject.learning_portal.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/learning-portal/courses")
public class CourseController {

    private static final Logger logger = LoggerFactory.getLogger(CourseController.class);

    @Autowired
    private CourseService courseService;

    @PostMapping("/create-course")
    public Course createCourse(@RequestBody CourseRequestDto courseRequestDto) {
        logger.info("Creating a new course with title: {}", courseRequestDto.getTitle());
        Course createdCourse = courseService.createCourse(
                courseRequestDto.getCourseCategory(),
                courseRequestDto.getDescription(),
                courseRequestDto.getPrice(),
                courseRequestDto.getTitle()
        );
        logger.info("Course created successfully with ID: {}", createdCourse.getCourseId());
        return createdCourse;
    }

    @GetMapping("/fetch-course/{id}")
    public CourseResponseDto getCourseDetailsById(@PathVariable("id") String courseId) {
        logger.info("Fetching course details for course ID: {}", courseId);
        CourseResponseDto courseResponse = courseService.getCourseDetailsById(courseId);
        if (courseResponse == null) {
            logger.warn("No course found with ID: {}", courseId);
        } else {
            logger.info("Course fetched successfully for course ID: {}", courseId);
        }
        return courseResponse;
    }

    @GetMapping("fetch-course/registered-course/{id}")
    public List<RegisteredCourses> getRegisteredCourseOfCourse(@PathVariable("id") String courseId) {
        logger.info("Fetching registered courses for course ID: {}", courseId);
        return courseService.getRegisteredCoursesOfCourse(courseId);
    }

    @DeleteMapping("/delete-course/{id}")
    public void deleteCourseById(@PathVariable("id") String courseId) {
        logger.info("Deleting course with ID: {}", courseId);
        courseService.deleteCourseById(courseId);
        logger.info("Course deleted successfully with ID: {}", courseId);
    }

    @GetMapping("/fetch-all-courses")
    public List<Course> getAllCourses() {
        logger.info("Fetching all courses");
        List<Course> courses = courseService.getAllCourses();
        logger.info("Fetched {} courses successfully", courses.size());
        return courses;
    }

    @PutMapping("/update/course-details/{id}")
    public Course updateCourseDetailsById(@PathVariable("id") String courseId, @RequestBody Course updatedCourseDetails) {
        logger.info("Updating course with ID: {}", courseId);
        Course updatedCourse = courseService.updateCourseDetailsById(courseId, updatedCourseDetails);
        if (updatedCourse == null) {
            logger.warn("No course found with ID: {}", courseId);
        } else {
            logger.info("Course updated successfully with ID: {}", updatedCourse.getCourseId());
        }
        return updatedCourse;
    }

}
