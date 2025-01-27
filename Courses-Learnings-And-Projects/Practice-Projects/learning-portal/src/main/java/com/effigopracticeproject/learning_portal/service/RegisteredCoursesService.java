package com.effigopracticeproject.learning_portal.service;

import com.effigopracticeproject.learning_portal.entity.Course;
import com.effigopracticeproject.learning_portal.entity.RegisteredCourses;
import com.effigopracticeproject.learning_portal.entity.User;
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

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    public RegisteredCourses addRegisteredCourse(RegisteredCourses registeredCourses) {
        logger.info("Registering a new course with User ID: {}, Course ID: {}", registeredCourses.getUser().getUserId(), registeredCourses.getCourse().getCourseId());
        RegisteredCourses savedCourse = registeredCoursesRepository.save(registeredCourses);
        logger.info("Course registered successfully with ID: {}", savedCourse.getRegistrationId());
        return savedCourse;
    }

    public List<RegisteredCourses> getAllRegisteredCourses() {
        logger.info("Fetching all registered courses");
        List<RegisteredCourses> registeredCoursesList = registeredCoursesRepository.findAll();
        logger.info("Fetched {} registered courses successfully", registeredCoursesList.size());
        return registeredCoursesList;
    }

    public Optional<RegisteredCourses> getRegisteredCourseById(String registeredCourseId) {
        logger.info("Fetching registered course with ID: {}", registeredCourseId);
        Optional<RegisteredCourses> registeredCourse = registeredCoursesRepository.findById(registeredCourseId);
        if (registeredCourse.isEmpty()) {
            logger.warn("No registered course found with ID: {}", registeredCourseId);
        } else {
            logger.info("Registered course fetched successfully with ID: {}", registeredCourseId);
        }
        return registeredCourse;
    }

    public RegisteredCourses updateRegisteredCourseById(String registeredCourseId, RegisteredCourses registeredCourseUpdateDetails) {
        logger.info("Updating registered course with ID: {}", registeredCourseId);
        RegisteredCourses registeredCourseToUpdate = registeredCoursesRepository.findById(registeredCourseId).orElse(null);
        if (registeredCourseToUpdate == null) {
            logger.warn("No registered course found with ID: {}", registeredCourseId);
            return null;
        }

        User user = userRepository.findById(registeredCourseUpdateDetails.getUser().getUserId()).orElse(null);
        Course course = courseRepository.findById(registeredCourseUpdateDetails.getCourse().getCourseId()).orElse(null);

        if (user == null || course == null) {
            logger.warn("Invalid User or Course data during update. User ID: {}, Course ID: {}", registeredCourseUpdateDetails.getUser().getUserId(), registeredCourseUpdateDetails.getCourse().getCourseId());
            return null;
        }

        registeredCourseToUpdate.setUser(user);
        registeredCourseToUpdate.setCourse(course);
        RegisteredCourses updatedRegisteredCourse = registeredCoursesRepository.save(registeredCourseToUpdate);
        logger.info("Registered course updated successfully with ID: {}", updatedRegisteredCourse.getRegistrationId());
        return updatedRegisteredCourse;
    }

    public void deleteRegisteredCourseById(String registeredCourseId) {
        logger.info("Deleting registered course with ID: {}", registeredCourseId);
        registeredCoursesRepository.deleteById(registeredCourseId);
        logger.info("Registered course deleted successfully with ID: {}", registeredCourseId);
    }
}
