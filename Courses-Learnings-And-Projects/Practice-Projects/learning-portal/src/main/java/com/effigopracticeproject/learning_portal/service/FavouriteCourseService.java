package com.effigopracticeproject.learning_portal.service;

import com.effigopracticeproject.learning_portal.entity.FavouriteCourse;
import com.effigopracticeproject.learning_portal.entity.RegisteredCourses;
import com.effigopracticeproject.learning_portal.repository.FavouriteCourseRepository;
import com.effigopracticeproject.learning_portal.repository.RegisteredCoursesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavouriteCourseService {

    private static final Logger logger = LoggerFactory.getLogger(FavouriteCourseService.class);

    @Autowired
    private FavouriteCourseRepository favouriteCourseRepository;

    @Autowired
    private RegisteredCoursesRepository registeredCoursesRepository;

    public FavouriteCourse addFavouriteCourse(FavouriteCourse favouriteCourse) {
        try {
            FavouriteCourse savedFavouriteCourse = favouriteCourseRepository.save(favouriteCourse);
            logger.info("Favourite course added successfully with ID: {}", savedFavouriteCourse.getFavouriteId());
            return savedFavouriteCourse;
        } catch (Exception e) {
            logger.error("Error occurred while adding favourite course: {}", e.getMessage(), e);
            throw new RuntimeException("Error occurred while adding favourite course: " + e.getMessage());
        }
    }

    public FavouriteCourse getFavouriteCourseById(String favouriteCourseId) {
        try {
            FavouriteCourse favouriteCourse = favouriteCourseRepository.findById(favouriteCourseId).orElse(null);
            if (favouriteCourse == null) {
                logger.warn("No favourite course found with ID: {}", favouriteCourseId);
            }
            return favouriteCourse;
        } catch (Exception e) {
            logger.error("Error occurred while fetching favourite course by ID: {}", e.getMessage(), e);
            throw new RuntimeException("Error occurred while fetching favourite course by ID: " + e.getMessage());
        }
    }

    public List<FavouriteCourse> getAllFavouriteCourses() {
        try {
            List<FavouriteCourse> favouriteCourses = favouriteCourseRepository.findAll();
            logger.info("Fetched {} favourite courses successfully", favouriteCourses.size());
            return favouriteCourses;
        } catch (Exception e) {
            logger.error("Error occurred while fetching all favourite courses: {}", e.getMessage(), e);
            throw new RuntimeException("Error occurred while fetching all favourite courses: " + e.getMessage());
        }
    }

    public void deleteFavouriteCourseById(String favouriteCourseId) {
        try {
            if (!favouriteCourseRepository.existsById(favouriteCourseId)) {
                logger.warn("No favourite course found with ID: {}", favouriteCourseId);
                return;
            }
            favouriteCourseRepository.deleteById(favouriteCourseId);
            logger.info("Favourite course deleted successfully with ID: {}", favouriteCourseId);
        } catch (Exception e) {
            logger.error("Error occurred while deleting favourite course with ID: {}", favouriteCourseId, e);
            throw new RuntimeException("Error occurred while deleting favourite course with ID: " + favouriteCourseId + ": " + e.getMessage());
        }
    }

    public FavouriteCourse updateFavouriteCourseById(String favouriteCourseId, FavouriteCourse favouriteCourseUpdateDetails) {
        try {
            FavouriteCourse favouriteCourseToUpdate = favouriteCourseRepository.findById(favouriteCourseId).orElse(null);
            if (favouriteCourseToUpdate == null) {
                logger.warn("No favourite course found with ID: {}", favouriteCourseId);
                throw new RuntimeException("No favourite course found with ID: " + favouriteCourseId);
            }

            RegisteredCourses registeredCourses = registeredCoursesRepository.findById(favouriteCourseUpdateDetails.getRegisteredCourses().getRegistrationId()).orElse(null);
            if (registeredCourses == null) {
                logger.warn("No registered course found with ID: {}", favouriteCourseUpdateDetails.getRegisteredCourses().getRegistrationId());
                throw new RuntimeException("No registered course found with ID: " + favouriteCourseUpdateDetails.getRegisteredCourses().getRegistrationId());
            }

            favouriteCourseToUpdate.setRegisteredCourses(registeredCourses);

            FavouriteCourse updatedFavouriteCourse = favouriteCourseRepository.save(favouriteCourseToUpdate);
            logger.info("Favourite course updated successfully with ID: {}", updatedFavouriteCourse.getFavouriteId());
            return updatedFavouriteCourse;
        } catch (Exception e) {
            logger.error("Error occurred while updating favourite course with ID: {}", favouriteCourseId, e);
            throw new RuntimeException("Error occurred while updating favourite course with ID: " + favouriteCourseId + ": " + e.getMessage());
        }
    }
}
