package com.effigopracticeproject.learning_portal.service;

import com.effigopracticeproject.learning_portal.entity.Course;
import com.effigopracticeproject.learning_portal.entity.RegisteredCourses;
import com.effigopracticeproject.learning_portal.entity.User;
import com.effigopracticeproject.learning_portal.exceptions.NoRegisteredCourseFoundException;
import com.effigopracticeproject.learning_portal.repository.CourseRepository;
import com.effigopracticeproject.learning_portal.repository.RegisteredCoursesRepository;
import com.effigopracticeproject.learning_portal.repository.UserRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import java.util.List;
import java.util.Optional;

@Service
public class RegisteredCoursesService {

    private static final Logger logger = LoggerFactory.getLogger(RegisteredCoursesService.class);

    @Autowired
    private RegisteredCoursesRepository registeredCoursesRepository;

    public RegisteredCourses addRegisteredCourse(RegisteredCourses registeredCourses) {
        logger.info("Registering course for user ID: {}, course ID: {}",
                registeredCourses.getUser().getUserId(),
                registeredCourses.getCourse().getCourseId());

        RegisteredCourses savedRegisteredCourse = registeredCoursesRepository.save(registeredCourses);
        logger.info("Course registered successfully with registration ID: {}", savedRegisteredCourse.getRegistrationId());
        return savedRegisteredCourse;
    }

    public Optional<RegisteredCourses> getRegisteredCourseById(String registeredCourseId) {
        logger.info("Fetching registered course with ID: {}", registeredCourseId);

        Optional<RegisteredCourses> registeredCourse = registeredCoursesRepository.findById(registeredCourseId);
        if (registeredCourse.isEmpty()) {
            logger.warn("No registered course found with ID: {}", registeredCourseId);
            throw new NoRegisteredCourseFoundException("No registered course found with ID: " + registeredCourseId);
        }

        logger.info("Successfully fetched registered course with ID: {}", registeredCourseId);
        return registeredCourse;
    }

    public List<RegisteredCourses> getAllRegisteredCourses() {
        logger.info("Fetching all registered courses");
        List<RegisteredCourses> registeredCoursesList = registeredCoursesRepository.findAll();
        logger.info("Fetched {} registered courses successfully", registeredCoursesList.size());
        return registeredCoursesList;
    }

    public void deleteRegisteredCourseById(String registeredCourseId) {
        logger.info("Deleting registered course with ID: {}", registeredCourseId);

        if (!registeredCoursesRepository.existsById(registeredCourseId)) {
            logger.warn("No registered course found with ID: {}", registeredCourseId);
            throw new NoRegisteredCourseFoundException("No registered course found with ID: " + registeredCourseId);
        }

        registeredCoursesRepository.deleteById(registeredCourseId);
        logger.info("Registered course deleted successfully with ID: {}", registeredCourseId);
    }

    public RegisteredCourses updateRegisteredCourseById(String registeredCourseId, RegisteredCourses registeredCourseUpdateDetails) {
        logger.info("Updating registered course with ID: {}", registeredCourseId);

        RegisteredCourses registeredCourse = registeredCoursesRepository.findById(registeredCourseId)
                .orElseThrow(() -> new NoRegisteredCourseFoundException("No registered course found with ID: " + registeredCourseId));

        registeredCourse.setUser(registeredCourseUpdateDetails.getUser());
        registeredCourse.setCourse(registeredCourseUpdateDetails.getCourse());

        RegisteredCourses updatedRegisteredCourse = registeredCoursesRepository.save(registeredCourse);
        logger.info("Registered course updated successfully with ID: {}", updatedRegisteredCourse.getRegistrationId());
        return updatedRegisteredCourse;
    }
}