package com.effigopracticeproject.learning_portal.service;

import com.effigopracticeproject.learning_portal.dto.CourseRequestDto;
import com.effigopracticeproject.learning_portal.dto.CourseResponseDto;
import com.effigopracticeproject.learning_portal.entity.Course;
import com.effigopracticeproject.learning_portal.entity.RegisteredCourses;
import com.effigopracticeproject.learning_portal.exceptions.NoCourseFoundException;
import com.effigopracticeproject.learning_portal.mapper.CourseRequestDtoMapper;
import com.effigopracticeproject.learning_portal.mapper.CourseResponseDtoMapper;
import com.effigopracticeproject.learning_portal.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService {

    private static final Logger logger = LoggerFactory.getLogger(CourseService.class);

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseRequestDtoMapper courseRequestDtoMapper;

    @Autowired
    private CourseResponseDtoMapper courseResponseDtoMapper;

    public Course createCourse(CourseRequestDto courseRequestDto) {
        logger.info("Creating a new course with title: {}", courseRequestDto.getTitle());

        Course course = courseRequestDtoMapper.courseRequestDtoToEntity(courseRequestDto);

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
            return courseResponseDtoMapper.courseEntityToDto(course);
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

    public List<CourseResponseDto> getAllCourses() {
        logger.info("Fetching all courses");

        // Fetch all courses from the repository
        List<Course> courses = courseRepository.findAll();

        // Map each Course entity to a CourseResponseDto and return the list
        return courses.stream()
                .map(courseResponseDtoMapper::courseEntityToDto)
                .collect(Collectors.toList());
    }

    public CourseResponseDto updateCourseDetailsById(String courseId, CourseRequestDto updatedCourseDto) {
        logger.info("Updating course with ID: {}", courseId);

        try {
            // Find existing course by ID
            Course existingCourse = courseRepository.findById(courseId)
                    .orElseThrow(() -> new NoCourseFoundException("No course found with ID: " + courseId));

            // Map updated details to the existing course
            Course updatedCourse = courseRequestDtoMapper.courseRequestDtoToEntity(updatedCourseDto);
            updatedCourse.setCourseId(courseId); // Ensure the course ID is not lost

            // Save the updated course
            updatedCourse = courseRepository.save(updatedCourse);

            // Map updated entity to DTO and return
            return courseResponseDtoMapper.courseEntityToDto(updatedCourse);
        } catch (NoCourseFoundException e) {
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