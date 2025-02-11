package com.effigopracticeproject.learning_portal.service;

import com.effigopracticeproject.learning_portal.dto.FavouriteCourseDto;
import com.effigopracticeproject.learning_portal.dto.FavouriteCourseRequestDto;
import com.effigopracticeproject.learning_portal.dto.FavouriteCourseResponseDto;
import com.effigopracticeproject.learning_portal.entity.FavouriteCourse;
import com.effigopracticeproject.learning_portal.entity.RegisteredCourses;
import com.effigopracticeproject.learning_portal.exceptions.NoFavouriteCourseFoundException;
import com.effigopracticeproject.learning_portal.exceptions.NoRegisteredCourseFoundException;
import com.effigopracticeproject.learning_portal.repository.FavouriteCourseRepository;
import com.effigopracticeproject.learning_portal.repository.RegisteredCoursesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FavouriteCourseService {

    private static final Logger logger = LoggerFactory.getLogger(FavouriteCourseService.class);

    @Autowired
    private FavouriteCourseRepository favouriteCourseRepository;

    @Autowired
    private RegisteredCoursesRepository registeredCoursesRepository;

    public FavouriteCourse addFavouriteCourse(FavouriteCourseRequestDto requestDto) {
        logger.info("Adding favourite course for registered course ID: {}", requestDto.getRegisteredCourseId());

        RegisteredCourses registeredCourses = registeredCoursesRepository.findById(requestDto.getRegisteredCourseId())
                .orElseThrow(() -> {
                    logger.warn("No registered course found with ID: {}", requestDto.getRegisteredCourseId());
                    return new NoRegisteredCourseFoundException("No registered course found with ID: " + requestDto.getRegisteredCourseId());
                });

        FavouriteCourse favouriteCourse = new FavouriteCourse();
        favouriteCourse.setRegisteredCourses(registeredCourses);

        FavouriteCourse createdFavouriteCourse = favouriteCourseRepository.save(favouriteCourse);
        return createdFavouriteCourse;
    }

    public FavouriteCourseResponseDto getFavouriteCourseById(String favouriteId) {
        logger.info("Fetching favourite course with ID: {}", favouriteId);

        FavouriteCourse favouriteCourse = favouriteCourseRepository.findById(favouriteId)
                .orElseThrow(() -> new NoFavouriteCourseFoundException("No favourite course found with ID: " + favouriteId));

        return new FavouriteCourseResponseDto(
                favouriteCourse.getFavouriteId(),
                favouriteCourse.getRegisteredCourses().getRegistrationId()
        );
    }

    public List<FavouriteCourseDto> getAllFavouriteCourses() {
        List<FavouriteCourse> favouriteCourses = favouriteCourseRepository.findAll();

        return favouriteCourses.stream().map(fav -> {
            String registeredCourseId = fav.getRegisteredCourses().getRegistrationId();

            // Fetch RegisteredCourse from DB
            RegisteredCourses registeredCourse = registeredCoursesRepository.findById(registeredCourseId).orElse(null);

            String username = (registeredCourse != null && registeredCourse.getUser() != null)
                    ? registeredCourse.getUser().getUsername()
                    : "Unknown User";

            String courseTitle = (registeredCourse != null && registeredCourse.getCourse() != null)
                    ? registeredCourse.getCourse().getTitle()
                    : "Unknown Course";

            return new FavouriteCourseDto(fav.getFavouriteId(), registeredCourse.getRegistrationId(), username, courseTitle);
        }).collect(Collectors.toList());
    }


    public void deleteFavouriteCourseById(String favouriteId) {
        logger.info("Deleting favourite course with ID: {}", favouriteId);

        if (!favouriteCourseRepository.existsById(favouriteId)) {
            logger.warn("No favourite course found with ID: {}", favouriteId);
            throw new NoFavouriteCourseFoundException("No favourite course found with ID: " + favouriteId);
        }

        favouriteCourseRepository.deleteById(favouriteId);
        logger.info("Favourite course deleted successfully");
    }
}
