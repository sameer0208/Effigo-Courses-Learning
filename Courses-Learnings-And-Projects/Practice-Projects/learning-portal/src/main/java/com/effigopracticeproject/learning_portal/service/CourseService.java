package com.effigopracticeproject.learning_portal.service;

import com.effigopracticeproject.learning_portal.dto.CourseResponseDto;
import com.effigopracticeproject.learning_portal.entity.Course;
import com.effigopracticeproject.learning_portal.entity.RegisteredCourses;
import com.effigopracticeproject.learning_portal.exceptions.NoCourseFoundException;
import com.effigopracticeproject.learning_portal.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

@Service
public class CourseService {

    private static final Logger logger = LoggerFactory.getLogger(CourseService.class);

    @Autowired
    private CourseRepository courseRepository;

    public Course createCourse(Long courseCategory, String description, Long price, String title) {
        logger.info("Creating a new course with title: {}", title);

        Course course = new Course();
        course.setCourseCategory(courseCategory);
        course.setDescription(description);
        course.setPrice(price);
        course.setTitle(title);

        Course savedCourse = courseRepository.save(course);
        logger.info("Course created successfully with ID: {}", savedCourse.getCourseId());
        return savedCourse;
    }

    public CourseResponseDto getCourseDetailsById(String courseId) {
        logger.info("Fetching course details for ID: {}", courseId);

        try {
            Course course = courseRepository.findById(courseId)
                    .orElseThrow(() -> new NoCourseFoundException("No course found with ID: " + courseId));

            logger.info("Course details fetched successfully for ID: {}", courseId);
            return new CourseResponseDto(
                    course.getCourseId(),
                    course.getCourseCategory(),
                    course.getDescription(),
                    course.getPrice(),
                    course.getTitle()
            );
        }
        catch (NoCourseFoundException e) {
            logger.error("Exception: {}", e.getMessage());
            throw e;
        }
    }

    public void deleteCourseById(String courseId) {
        logger.info("Deleting course with ID: {}", courseId);
        try {
            if (!courseRepository.existsById(courseId)) {
                throw new NoCourseFoundException("No course found with ID: " + courseId);
            }
            courseRepository.deleteById(courseId);
            logger.info("Course deleted successfully with ID: {}", courseId);
        } catch (NoCourseFoundException e) {
            logger.error("Exception: {}", e.getMessage());
            throw e;
        }
    }

    public List<Course> getAllCourses() {
        logger.info("Fetching all courses");
        return courseRepository.findAll();
    }

    public Course updateCourseDetailsById(String courseId, Course updatedCourseDetails) {
        logger.info("Updating course with ID: {}", courseId);

        try {
            Course courseToUpdate = courseRepository.findById(courseId)
                    .orElseThrow(() -> new NoCourseFoundException("No course found with ID: " + courseId));

            courseToUpdate.setCourseCategory(updatedCourseDetails.getCourseCategory());
            courseToUpdate.setDescription(updatedCourseDetails.getDescription());
            courseToUpdate.setPrice(updatedCourseDetails.getPrice());
            courseToUpdate.setTitle(updatedCourseDetails.getTitle());

            Course updatedCourse = courseRepository.save(courseToUpdate);
            logger.info("Course updated successfully with ID: {}", updatedCourse.getCourseId());
            return updatedCourse;
        }
        catch (NoCourseFoundException e) {
            logger.error("Exception: {}", e.getMessage());
            throw e;
        }
    }

    public List<RegisteredCourses> getRegisteredCoursesOfCourse(String courseId) {
        logger.info("Fetching registered courses for course ID: {}", courseId);

        try {
            Course course = courseRepository.findById(courseId)
                    .orElseThrow(() -> new NoCourseFoundException("No course found with ID: " + courseId));

            logger.info("Registered courses fetched successfully for course ID: {}", courseId);
            return course.getRegisteredCourses();
        }
        catch (NoCourseFoundException e) {
            logger.error("Exception: {}", e.getMessage());
            throw e;
        }
    }
}