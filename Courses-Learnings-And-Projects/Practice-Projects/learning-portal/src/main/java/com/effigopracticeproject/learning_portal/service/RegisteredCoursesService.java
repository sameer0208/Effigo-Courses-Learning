package com.effigopracticeproject.learning_portal.service;

import com.effigopracticeproject.learning_portal.entity.RegisteredCourses;
import com.effigopracticeproject.learning_portal.exceptions.NoRegisteredCourseFoundException;
import com.effigopracticeproject.learning_portal.repository.RegisteredCoursesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegisteredCoursesService {

    private static final Logger logger = LoggerFactory.getLogger(RegisteredCoursesService.class);

    @Autowired
    private RegisteredCoursesRepository registeredCoursesRepository;

    public RegisteredCourses addRegisteredCourse(RegisteredCourses registeredCourses) {
        try {
            logger.info("Registering course for user ID: {}, course ID: {}",
                    registeredCourses.getUser().getUserId(),
                    registeredCourses.getCourse().getCourseId());

            RegisteredCourses savedRegisteredCourse = registeredCoursesRepository.save(registeredCourses);
            logger.info("Course registered successfully with registration ID: {}", savedRegisteredCourse.getRegistrationId());
            return savedRegisteredCourse;
        } catch (Exception e) {
            logger.error("Error occurred while registering course: {}", e.getMessage(), e);
            throw new RuntimeException("Error occurred while registering course: " + e.getMessage());
        }
    }

    public Optional<RegisteredCourses> getRegisteredCourseById(String registeredCourseId) {
        try {
            logger.info("Fetching registered course with ID: {}", registeredCourseId);

            Optional<RegisteredCourses> registeredCourse = registeredCoursesRepository.findById(registeredCourseId);
            if (registeredCourse.isEmpty()) {
                logger.warn("No registered course found with ID: {}", registeredCourseId);
                throw new NoRegisteredCourseFoundException("No registered course found with ID: " + registeredCourseId);
            }

            logger.info("Successfully fetched registered course with ID: {}", registeredCourseId);
            return registeredCourse;
        } catch (NoRegisteredCourseFoundException e) {
            logger.warn("No registered course found with ID: {}", registeredCourseId);
            throw e; // Re-throwing the custom exception after logging
        } catch (Exception e) {
            logger.error("Error occurred while fetching registered course by ID: {}", e.getMessage(), e);
            throw new RuntimeException("Error occurred while fetching registered course by ID: " + e.getMessage());
        }
    }

    public List<RegisteredCourses> getAllRegisteredCourses() {
        try {
            logger.info("Fetching all registered courses");
            List<RegisteredCourses> registeredCoursesList = registeredCoursesRepository.findAll();
            logger.info("Fetched {} registered courses successfully", registeredCoursesList.size());
            return registeredCoursesList;
        } catch (Exception e) {
            logger.error("Error occurred while fetching all registered courses: {}", e.getMessage(), e);
            throw new RuntimeException("Error occurred while fetching all registered courses: " + e.getMessage());
        }
    }

    public void deleteRegisteredCourseById(String registeredCourseId) {
        try {
            logger.info("Deleting registered course with ID: {}", registeredCourseId);

            if (!registeredCoursesRepository.existsById(registeredCourseId)) {
                logger.warn("No registered course found with ID: {}", registeredCourseId);
                throw new NoRegisteredCourseFoundException("No registered course found with ID: " + registeredCourseId);
            }

            registeredCoursesRepository.deleteById(registeredCourseId);
            logger.info("Registered course deleted successfully with ID: {}", registeredCourseId);
        } catch (NoRegisteredCourseFoundException e) {
            logger.warn("No registered course found with ID: {}", registeredCourseId);
            throw e; // Re-throwing the custom exception after logging
        } catch (Exception e) {
            logger.error("Error occurred while deleting registered course: {}", e.getMessage(), e);
            throw new RuntimeException("Error occurred while deleting registered course: " + e.getMessage());
        }
    }

    public RegisteredCourses updateRegisteredCourseById(String registeredCourseId, RegisteredCourses registeredCourseUpdateDetails) {
        try {
            logger.info("Updating registered course with ID: {}", registeredCourseId);

            RegisteredCourses registeredCourse = registeredCoursesRepository.findById(registeredCourseId)
                    .orElseThrow(() -> new NoRegisteredCourseFoundException("No registered course found with ID: " + registeredCourseId));

            registeredCourse.setUser(registeredCourseUpdateDetails.getUser());
            registeredCourse.setCourse(registeredCourseUpdateDetails.getCourse());

            RegisteredCourses updatedRegisteredCourse = registeredCoursesRepository.save(registeredCourse);
            logger.info("Registered course updated successfully with ID: {}", updatedRegisteredCourse.getRegistrationId());
            return updatedRegisteredCourse;
        } catch (NoRegisteredCourseFoundException e) {
            logger.warn("No registered course found with ID: {}", registeredCourseId);
            throw e;
        } catch (Exception e) {
            logger.error("Error occurred while updating registered course: {}", e.getMessage(), e);
            throw new RuntimeException("Error occurred while updating registered course: " + e.getMessage());
        }
    }

    public List<RegisteredCourses> getRegisteredCourseByUserAndCourse(String userId, String courseId) {
        logger.info("Fetching registered courses for userId: {} and courseId: {}", userId, courseId);

        List<RegisteredCourses> registeredCourses = registeredCoursesRepository.findByUserIdAndCourseId(userId, courseId);

        if (registeredCourses.isEmpty()) {
            logger.warn("No registered courses found for userId: {} and courseId: {}", userId, courseId);
            throw new NoRegisteredCourseFoundException("No registered courses found for the given user and course.");
        }

        return registeredCourses;
    }


}
