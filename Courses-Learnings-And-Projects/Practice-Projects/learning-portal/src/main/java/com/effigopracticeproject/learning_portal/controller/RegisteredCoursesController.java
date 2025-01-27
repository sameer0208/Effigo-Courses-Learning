package com.effigopracticeproject.learning_portal.controller;

import com.effigopracticeproject.learning_portal.dto.RegisteredCoursesRequestDto;
import com.effigopracticeproject.learning_portal.dto.RegisteredCoursesResponseDto;
import com.effigopracticeproject.learning_portal.entity.Course;
import com.effigopracticeproject.learning_portal.entity.RegisteredCourses;
import com.effigopracticeproject.learning_portal.entity.User;
import com.effigopracticeproject.learning_portal.repository.CourseRepository;
import com.effigopracticeproject.learning_portal.repository.UserRepository;
import com.effigopracticeproject.learning_portal.service.RegisteredCoursesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/learning-portal/registered-courses")
public class RegisteredCoursesController {

    private static final Logger logger = LoggerFactory.getLogger(RegisteredCoursesController.class);

    @Autowired
    private RegisteredCoursesService registeredCoursesService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @PostMapping("/course-registration")
    public RegisteredCourses addRegisteredCourse(@RequestBody RegisteredCourses registeredCourses) {
        logger.info("Starting registration of course with ID: {}", registeredCourses.getCourse().getCourseId());

        User user = userRepository.findById(registeredCourses.getUser().getUserId()).orElse(null);
        Course course = courseRepository.findById(registeredCourses.getCourse().getCourseId()).orElse(null);

        if (user == null || course == null) {
            logger.warn("User or Course not found. User ID: {}, Course ID: {}", registeredCourses.getUser().getUserId(), registeredCourses.getCourse().getCourseId());
            return null;
        }

        registeredCourses.setUser(user);
        registeredCourses.setCourse(course);
        RegisteredCourses registeredCourse = registeredCoursesService.addRegisteredCourse(registeredCourses);
        logger.info("Successfully registered course with ID: {}", registeredCourse.getRegistrationId());
        return registeredCourse;
    }

    @GetMapping("/fetch-registered-course/{id}")
    public Optional<RegisteredCourses> getRegisteredCourseById(@PathVariable("id") String registeredCourseId) {
        logger.info("Fetching registered course with ID: {}", registeredCourseId);
        Optional<RegisteredCourses> registeredCourse = registeredCoursesService.getRegisteredCourseById(registeredCourseId);
        if (registeredCourse.isEmpty()) {
            logger.warn("No registered course found with ID: {}", registeredCourseId);
        } else {
            logger.info("Registered course fetched successfully with ID: {}", registeredCourseId);
        }
        return registeredCourse;
    }

    @GetMapping("/fetch-registered-courses")
    public List<RegisteredCourses> getAllRegisteredCourses() {
        logger.info("Fetching all registered courses");
        List<RegisteredCourses> registeredCoursesList = registeredCoursesService.getAllRegisteredCourses();
        logger.info("Fetched {} registered courses successfully", registeredCoursesList.size());
        return registeredCoursesList;
    }

    @DeleteMapping("/delete-registered-course/{id}")
    public void deleteRegisteredCourseById(@PathVariable("id") String registeredCourseId) {
        logger.info("Deleting registered course with ID: {}", registeredCourseId);
        registeredCoursesService.deleteRegisteredCourseById(registeredCourseId);
        logger.info("Registered course deleted successfully with ID: {}", registeredCourseId);
    }

    @PutMapping("/update-registered-course/{id}")
    public RegisteredCourses updateRegisteredCourse(@PathVariable("id") String registeredCourseId, @RequestBody RegisteredCourses registeredCourseUpdateDetails) {
        logger.info("Updating registered course with ID: {}", registeredCourseId);
        RegisteredCourses updatedRegisteredCourse = registeredCoursesService.updateRegisteredCourseById(registeredCourseId, registeredCourseUpdateDetails);
        if (updatedRegisteredCourse == null) {
            logger.warn("No registered course found for update with ID: {}", registeredCourseId);
        } else {
            logger.info("Registered course updated successfully with ID: {}", updatedRegisteredCourse.getRegistrationId());
        }
        return updatedRegisteredCourse;
    }
}
