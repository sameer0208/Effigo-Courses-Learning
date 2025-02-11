package com.effigopracticeproject.learning_portal.controller;

import com.effigopracticeproject.learning_portal.dto.FavouriteCourseDto;
import com.effigopracticeproject.learning_portal.dto.FavouriteCourseRequestDto;
import com.effigopracticeproject.learning_portal.dto.FavouriteCourseResponseDto;
import com.effigopracticeproject.learning_portal.entity.FavouriteCourse;
import com.effigopracticeproject.learning_portal.entity.RegisteredCourses;
import com.effigopracticeproject.learning_portal.exceptions.NoFavouriteCourseFoundException;
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

    @PostMapping("/add-favourite-course")
    public ResponseEntity<FavouriteCourse> addFavouriteCourse(@RequestBody FavouriteCourseRequestDto requestDto) {
        try {
            logger.info("Received request to add favourite course: RegisteredCourse ID - {}", requestDto.getRegisteredCourseId());

            FavouriteCourse createdCourse = favouriteCourseService.addFavouriteCourse(requestDto);
            return new ResponseEntity<>(createdCourse, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error adding favourite course: {}", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/fetch-favourite-course/{id}")
    public ResponseEntity<FavouriteCourseResponseDto> getFavouriteCourseById(@PathVariable("id") String favouriteId) {
        try {
            FavouriteCourseResponseDto favouriteCourseDto = favouriteCourseService.getFavouriteCourseById(favouriteId);
            return new ResponseEntity<>(favouriteCourseDto, HttpStatus.OK);
        } catch (NoFavouriteCourseFoundException e) {
            logger.warn("No favourite course found with ID: {}", favouriteId);
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/fetch-all-favourite-courses")
    public ResponseEntity<List<FavouriteCourseDto>> getAllFavouriteCourses() {
        logger.info("Fetching all favourite courses");

//        List<FavouriteCourseResponseDto> favouriteCourses = favouriteCourseService.getAllFavouriteCourses();

//        if (favouriteCourses.isEmpty()) {
//            return new ResponseEntity<>(favouriteCourses, HttpStatus.NO_CONTENT);
//        }
//
//        return new ResponseEntity<>(favouriteCourses, HttpStatus.OK);
       return new  ResponseEntity<>(favouriteCourseService.getAllFavouriteCourses(), HttpStatus.OK);
    }

    @DeleteMapping("/delete-favourite-course/{id}")
    public ResponseEntity<String> deleteFavouriteCourseById(@PathVariable("id") String favouriteId) {
        try {
            favouriteCourseService.deleteFavouriteCourseById(favouriteId);
            return new ResponseEntity<>("Favourite course deleted successfully", HttpStatus.NO_CONTENT);
        } catch (NoFavouriteCourseFoundException e) {
            logger.warn("No favourite course found with ID: {}", favouriteId);
            return new ResponseEntity<>("Favourite course not found", HttpStatus.NOT_FOUND);
        }
    }
}