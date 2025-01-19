package com.effigocourse.springboot.learn_spring_boot;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class CourseController {

    @RequestMapping("/courses")
    public List<Course> retrieveAllCourses()
    {
        return Arrays.asList(
                new Course(1,"Learn AWS","Effigo"),
                new Course(2,"Learn DevOps","Effigo"),
                new Course(3, "Azure","Effigo")
        );
    }
}
