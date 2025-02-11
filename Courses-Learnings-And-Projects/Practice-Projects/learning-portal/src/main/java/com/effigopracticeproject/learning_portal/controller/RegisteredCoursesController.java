    package com.effigopracticeproject.learning_portal.controller;

    import com.effigopracticeproject.learning_portal.dto.RegisteredCoursesRequestDto;
    import com.effigopracticeproject.learning_portal.dto.RegisteredCoursesResponseDto;
    import com.effigopracticeproject.learning_portal.entity.Course;
    import com.effigopracticeproject.learning_portal.entity.RegisteredCourses;
    import com.effigopracticeproject.learning_portal.entity.User;
    import com.effigopracticeproject.learning_portal.exceptions.NoRegisteredCourseFoundException;
    import com.effigopracticeproject.learning_portal.service.RegisteredCoursesService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.MediaType;
    import org.springframework.http.ResponseEntity;
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

        @PostMapping("/course-registration")
        public ResponseEntity<RegisteredCourses> addRegisteredCourse(@RequestBody RegisteredCoursesRequestDto registeredCoursesRequestDto) {
            try {
                logger.info("Received registration request: User ID - {}, Course ID - {}",
                        registeredCoursesRequestDto.getUserId(), registeredCoursesRequestDto.getCourseId());

                // Create User and Course objects manually
                User user = new User();
                user.setUserId(registeredCoursesRequestDto.getUserId());
                logger.info("checking user id value here: {}", registeredCoursesRequestDto.getUserId());
                Course course = new Course();
                course.setCourseId(registeredCoursesRequestDto.getCourseId());
                logger.info("checking course id value here: {}", registeredCoursesRequestDto.getCourseId());
                // Create RegisteredCourses object
                RegisteredCourses registeredCourses = new RegisteredCourses();
                registeredCourses.setUser(user);
                registeredCourses.setCourse(course);

                RegisteredCourses createdCourse = registeredCoursesService.addRegisteredCourse(registeredCourses);
                return new ResponseEntity<>(createdCourse, HttpStatus.CREATED);
            } catch (Exception e) {
                logger.error("Error registering course: {}", e.getMessage());
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }


        @GetMapping("/fetch-registered-course/{id}")
        public ResponseEntity<RegisteredCoursesResponseDto> getRegisteredCourseById(@PathVariable("id") String registeredCourseId) {
            try {
                RegisteredCoursesResponseDto registeredCourseDto = registeredCoursesService.getRegisteredCourseById(registeredCourseId);
                return new ResponseEntity<>(registeredCourseDto, HttpStatus.OK);
            } catch (NoRegisteredCourseFoundException e) {
                logger.warn("No registered course found with ID: {}", registeredCourseId);
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        }


        @GetMapping("/fetch-registered-courses")
        public ResponseEntity<List<RegisteredCoursesResponseDto>> getAllRegisteredCourses() {
            logger.info("Fetching all registered courses");

            List<RegisteredCoursesResponseDto> registeredCourses = registeredCoursesService.getAllRegisteredCourses();

            if (registeredCourses.isEmpty()) {
                return new ResponseEntity<>(registeredCourses, HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(registeredCourses, HttpStatus.OK);
        }


        @DeleteMapping("/delete-registered-course/{id}")
        public ResponseEntity<String> deleteRegisteredCourseById(@PathVariable("id") String registeredCourseId) {
            try {
                registeredCoursesService.deleteRegisteredCourseById(registeredCourseId);
                return new ResponseEntity<>("Registered course deleted successfully", HttpStatus.NO_CONTENT);
            } catch (NoRegisteredCourseFoundException e) {
                logger.warn("No registered course found with ID: {}", registeredCourseId);
                return new ResponseEntity<>("Registered course not found", HttpStatus.NOT_FOUND);
            }
        }

        @PutMapping("/update-registered-course/{id}")
        public ResponseEntity<RegisteredCourses> updateRegisteredCourse(@PathVariable("id") String registeredCourseId, @RequestBody RegisteredCourses registeredCourseUpdateDetails) {
            try {
                RegisteredCourses updatedCourse = registeredCoursesService.updateRegisteredCourseById(registeredCourseId, registeredCourseUpdateDetails);
                return new ResponseEntity<>(updatedCourse, HttpStatus.OK);
            } catch (NoRegisteredCourseFoundException e) {
                logger.warn("No registered course found with ID: {}", registeredCourseId);
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        }

        @GetMapping("/fetch-by-user-and-course")
        public ResponseEntity<List<RegisteredCourses>> getRegisteredCourses(
                @RequestParam("userId") String userId,
                @RequestParam("courseId") String courseId) {

            logger.info("Received request to fetch registered courses for userId: {} and courseId: {}", userId, courseId);

            List<RegisteredCourses> registeredCourses = registeredCoursesService.getRegisteredCourseByUserAndCourse(userId, courseId);

            return new ResponseEntity<>(registeredCourses, HttpStatus.OK);
        }
    }
