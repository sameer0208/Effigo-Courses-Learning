package com.effigopracticeproject.learning_portal.service;

import com.effigopracticeproject.learning_portal.dto.RegisteredCoursesResponseDto;
import com.effigopracticeproject.learning_portal.entity.Course;
import com.effigopracticeproject.learning_portal.entity.RegisteredCourses;
import com.effigopracticeproject.learning_portal.entity.User;
import com.effigopracticeproject.learning_portal.exceptions.NoRegisteredCourseFoundException;
import com.effigopracticeproject.learning_portal.repository.CourseRepository;
import com.effigopracticeproject.learning_portal.repository.RegisteredCoursesRepository;
import com.effigopracticeproject.learning_portal.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    try {
        if (registeredCourses.getUser() == null || registeredCourses.getUser().getUserId() == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }
        if (registeredCourses.getCourse() == null || registeredCourses.getCourse().getCourseId() == null) {
            throw new IllegalArgumentException("Course ID cannot be null");
        }

        // Fetch user from DB to make sure it's managed by JPA
        User user = userRepository.findById(registeredCourses.getUser().getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + registeredCourses.getUser().getUserId()));

        // Fetch course from DB to make sure it's managed by JPA
        Course course = courseRepository.findById(registeredCourses.getCourse().getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found with ID: " + registeredCourses.getCourse().getCourseId()));

        // Attach user and course to the registeredCourses object
        registeredCourses.setUser(user);
        registeredCourses.setCourse(course);

        // Persist the entity
        RegisteredCourses savedRegisteredCourse = registeredCoursesRepository.save(registeredCourses);
        return savedRegisteredCourse;
    } catch (Exception e) {
        throw new RuntimeException("Error occurred while registering course: " + e.getMessage(), e);
    }
}
    public RegisteredCoursesResponseDto getRegisteredCourseById(String registeredCourseId) {
        logger.info("Fetching registered course with ID: {}", registeredCourseId);

        RegisteredCourses registeredCourse = registeredCoursesRepository.findById(registeredCourseId)
                .orElseThrow(() -> new NoRegisteredCourseFoundException("No registered course found with ID: " + registeredCourseId));

        return new RegisteredCoursesResponseDto(
                registeredCourse.getRegistrationId(),
                registeredCourse.getUser().getUserId(),
                registeredCourse.getUser().getUsername(), // ✅ Fetch username
                registeredCourse.getCourse().getCourseId(),
                registeredCourse.getCourse().getTitle()
        );
    }


    public List<RegisteredCoursesResponseDto> getAllRegisteredCourses() {
        logger.info("Fetching all registered courses");
        List<RegisteredCourses> registeredCoursesList = registeredCoursesRepository.findAll();

        return registeredCoursesList.stream().map(registeredCourse ->
                new RegisteredCoursesResponseDto(
                        registeredCourse.getRegistrationId(),
                        registeredCourse.getUser().getUserId(),
                        registeredCourse.getUser().getUsername(), // ✅ Fetch username
                        registeredCourse.getCourse().getCourseId(),
                        registeredCourse.getCourse().getTitle() // ✅ Fetch course title
                )).toList();
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
