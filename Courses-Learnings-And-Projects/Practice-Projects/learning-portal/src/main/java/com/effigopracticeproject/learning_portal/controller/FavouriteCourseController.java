package com.effigopracticeproject.learning_portal.controller;

import com.effigopracticeproject.learning_portal.entity.FavouriteCourse;
import com.effigopracticeproject.learning_portal.entity.RegisteredCourses;
import com.effigopracticeproject.learning_portal.exceptions.NoFavouriteCourseFoundException;
import com.effigopracticeproject.learning_portal.repository.RegisteredCoursesRepository;
import com.effigopracticeproject.learning_portal.service.FavouriteCourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/learning-portal/favourite-courses")
public class FavouriteCourseController {

    private static final Logger logger = LoggerFactory.getLogger(FavouriteCourseController.class);

    @Autowired
    private FavouriteCourseService favouriteCourseService;

    @Autowired
    private RegisteredCoursesRepository registeredCoursesRepository;

    @PostMapping("/add-favourite-course")
    public ResponseEntity<FavouriteCourse> addFavouriteCourse(@RequestBody FavouriteCourse favouriteCourse) {
        logger.info("Adding favorite course for registered course ID: {}", favouriteCourse.getRegisteredCourses().getRegistrationId());

        RegisteredCourses registeredCourses = registeredCoursesRepository.findById(favouriteCourse.getRegisteredCourses().getRegistrationId())
                .orElseThrow(() -> {
                    logger.warn("No registered course found with ID: {}", favouriteCourse.getRegisteredCourses().getRegistrationId());
                    return new NoFavouriteCourseFoundException("No registered course found with ID: " + favouriteCourse.getRegisteredCourses().getRegistrationId());
                });

        favouriteCourse.setRegisteredCourses(registeredCourses);
        FavouriteCourse createdFavouriteCourse = favouriteCourseService.addFavouriteCourse(favouriteCourse);
        return new ResponseEntity<>(createdFavouriteCourse, HttpStatus.CREATED);
    }

    @GetMapping("/fetch-favourite-course/{id}")
    public ResponseEntity<FavouriteCourse> getFavouriteCourseById(@PathVariable("id") String favouriteCourseId) {
        try {
            FavouriteCourse favouriteCourse = favouriteCourseService.getFavouriteCourseById(favouriteCourseId);
            return new ResponseEntity<>(favouriteCourse, HttpStatus.OK);
        } catch (NoFavouriteCourseFoundException e) {
            logger.warn("No favorite course found with ID: {}", favouriteCourseId);
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/fetch-favourite-courses")
    public ResponseEntity<List<FavouriteCourse>> getAllFavouriteCourses() {
        logger.info("Fetching all favorite courses");
        List<FavouriteCourse> favouriteCourses = favouriteCourseService.getAllFavouriteCourses();
        if (favouriteCourses.isEmpty()) {
            return new ResponseEntity<>(favouriteCourses, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(favouriteCourses, HttpStatus.OK);
    }

    @DeleteMapping("/delete-favourite-course/{id}")
    public ResponseEntity<String> deleteFavouriteCourseById(@PathVariable("id") String favouriteCourseId) {
        try {
            favouriteCourseService.deleteFavouriteCourseById(favouriteCourseId);
            return new ResponseEntity<>("Favorite course deleted successfully", HttpStatus.NO_CONTENT);
        } catch (NoFavouriteCourseFoundException e) {
            logger.warn("No favorite course found with ID: {}", favouriteCourseId);
            return new ResponseEntity<>("Favorite course not found", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update-favourite-course/{id}")
    public ResponseEntity<FavouriteCourse> updateFavouriteCourseById(@PathVariable("id") String favouriteCourseId, @RequestBody FavouriteCourse favouriteCourse) {
        try {
            FavouriteCourse updatedFavouriteCourse = favouriteCourseService.updateFavouriteCourseById(favouriteCourseId, favouriteCourse);
            return new ResponseEntity<>(updatedFavouriteCourse, HttpStatus.OK);
        } catch (NoFavouriteCourseFoundException e) {
            logger.warn("No favorite course found with ID: {}", favouriteCourseId);
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
