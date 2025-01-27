package com.effigopracticeproject.learning_portal.controller;

import com.effigopracticeproject.learning_portal.dto.FavouriteCourseRequestDto;
import com.effigopracticeproject.learning_portal.dto.FavouriteCourseResponseDto;
import com.effigopracticeproject.learning_portal.entity.FavouriteCourse;
import com.effigopracticeproject.learning_portal.entity.RegisteredCourses;
import com.effigopracticeproject.learning_portal.repository.RegisteredCoursesRepository;
import com.effigopracticeproject.learning_portal.service.FavouriteCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/learning-portal/favourite-courses")
public class FavouriteCourseController {
    @Autowired
    private FavouriteCourseService favouriteCourseService;

    @Autowired
    private RegisteredCoursesRepository registeredCoursesRepository;

    @PostMapping("/add-favourite-course")
    public FavouriteCourse addFavouriteCourse(@RequestBody FavouriteCourse favouriteCourse)
    {
        RegisteredCourses registeredCourses = registeredCoursesRepository.findById(favouriteCourse.getRegisteredCourses().getRegistrationId()).orElse(null);
        favouriteCourse.setRegisteredCourses(registeredCourses);

        return favouriteCourseService.addFavouriteCourse(favouriteCourse);
    }

    @GetMapping("/fetch-favourite-course/{id}")
    public FavouriteCourse getFavouriteCourseById(@PathVariable("id") String favouriteCourseId)
    {
        return favouriteCourseService.getFavouriteCourseById(favouriteCourseId);
    }

    @GetMapping("/fetch-favourite-courses")
    public List<FavouriteCourse> getAllFavouriteCourse()
    {
        return favouriteCourseService.getAllFavouriteCourses();
    }

    @DeleteMapping("/delete-favourite-course/{id}")
    public void deleteFavouriteCourseById(@PathVariable("id") String favouriteCourseId)
    {
        favouriteCourseService.deleteFavouriteCourseById(favouriteCourseId);
    }

    @PutMapping("/update-favourite-course/{id}")
    public FavouriteCourse updateFavouriteCourseById(@PathVariable("id") String favouriteCourseId, @RequestBody FavouriteCourse favouriteCourse)
    {
        return favouriteCourseService.updateFavouriteCourseById(favouriteCourseId, favouriteCourse);
    }

}
