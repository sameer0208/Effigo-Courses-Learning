package com.effigopracticeproject.learning_portal.controller;

import com.effigopracticeproject.learning_portal.dto.CourseRequestDto;
import com.effigopracticeproject.learning_portal.dto.CourseResponseDto;
import com.effigopracticeproject.learning_portal.entity.Course;
import com.effigopracticeproject.learning_portal.entity.RegisteredCourses;
import com.effigopracticeproject.learning_portal.exceptions.NoCourseFoundException;
import com.effigopracticeproject.learning_portal.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

@RestController
@RequestMapping("/learning-portal/courses")
public class CourseController {

    private static final Logger logger = LoggerFactory.getLogger(CourseController.class);

    @Autowired
    private CourseService courseService;

    @PostMapping("/create-course")
    public ResponseEntity<Course> createCourse(@RequestBody CourseRequestDto courseRequestDto) {
        logger.info("Creating a new course with title: {}", courseRequestDto.getTitle());
        try {
            Course createdCourse = courseService.createCourse(courseRequestDto);
            return new ResponseEntity<>(createdCourse, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error creating course: {}", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/fetch-course/{id}")
    public ResponseEntity<CourseResponseDto> getCourseDetailsById(@PathVariable("id") String courseId) {
        try {
            CourseResponseDto courseResponse = courseService.getCourseDetailsById(courseId);
            return new ResponseEntity<>(courseResponse, HttpStatus.OK);
        } catch (NoCourseFoundException e) {
            logger.warn("No course found with ID: {}", courseId);
//            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/fetch-course/registered-course/{id}")
    public ResponseEntity<List<RegisteredCourses>> getRegisteredCourseOfCourse(@PathVariable("id") String courseId) {
        try {
            List<RegisteredCourses> courses = courseService.getRegisteredCoursesOfCourse(courseId);
            if (courses.isEmpty()) {
                return new ResponseEntity<>(courses, HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(courses, HttpStatus.OK);
        } catch (NoCourseFoundException e) {
            logger.warn("No course found with ID: {}", courseId);
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete-course/{id}")
    public ResponseEntity<String> deleteCourseById(@PathVariable("id") String courseId) {
        try {
            courseService.deleteCourseById(courseId);
            return new ResponseEntity<>("Course deleted successfully", HttpStatus.NO_CONTENT);
        } catch (NoCourseFoundException e) {
            logger.warn("No course found with ID: {}", courseId);
            return new ResponseEntity<>("Course not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/fetch-all-courses")
    public ResponseEntity<List<CourseResponseDto>> getAllCourses() {
        List<CourseResponseDto> courses = courseService.getAllCourses();
        if (courses.isEmpty()) {
            return new ResponseEntity<>(courses, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }


    @PutMapping("/update/course-details/{id}")
    public ResponseEntity<CourseResponseDto> updateCourseDetailsById(
            @PathVariable("id") String courseId, @RequestBody CourseRequestDto updatedCourseDetails) {
        try {
            CourseResponseDto updatedCourse = courseService.updateCourseDetailsById(courseId, updatedCourseDetails);
            return new ResponseEntity<>(updatedCourse, HttpStatus.OK);
        } catch (NoCourseFoundException e) {
            logger.warn("No course found with ID: {}", courseId);
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
