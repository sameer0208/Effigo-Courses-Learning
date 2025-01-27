package com.effigopracticeproject.learning_portal.service;

import com.effigopracticeproject.learning_portal.entity.FavouriteCourse;
import com.effigopracticeproject.learning_portal.entity.RegisteredCourses;
import com.effigopracticeproject.learning_portal.repository.FavouriteCourseRepository;
import com.effigopracticeproject.learning_portal.repository.RegisteredCoursesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavouriteCourseService {
    @Autowired
    private FavouriteCourseRepository favouriteCourseRepository;

    @Autowired
    private RegisteredCoursesRepository registeredCoursesRepository;

    public FavouriteCourse addFavouriteCourse(FavouriteCourse favouriteCourse)
    {
        return favouriteCourseRepository.save(favouriteCourse);
    }

    public FavouriteCourse getFavouriteCourseById(String favouriteCourseId)
    {
        return favouriteCourseRepository.findById(favouriteCourseId).orElse(null);
    }

    public List<FavouriteCourse> getAllFavouriteCourses()
    {
        return favouriteCourseRepository.findAll();
    }

    public void deleteFavouriteCourseById(String favouriteCourseId)
    {
        favouriteCourseRepository.deleteById(favouriteCourseId);
    }

    public FavouriteCourse updateFavouriteCourseById(String favouriteCourseId,FavouriteCourse favouriteCourseUpdateDetails)
    {
        FavouriteCourse favouriteCourseToUpdate = favouriteCourseRepository.findById(favouriteCourseId).orElse(null);
        RegisteredCourses registeredCourses = registeredCoursesRepository.findById(favouriteCourseUpdateDetails.getRegisteredCourses().getRegistrationId()).orElse(null);
        favouriteCourseToUpdate.setRegisteredCourses(registeredCourses);
        return favouriteCourseRepository.save(favouriteCourseToUpdate);
    }

}
