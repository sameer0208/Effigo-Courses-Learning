package com.effigocourse.springboot.learn_spring_boot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class InternController {

    @RequestMapping("/effigo/interns")
    public List<Intern> retrieveInternDetails()
    {
        return Arrays.asList(
                new Intern(1,"Sameer","Full Stack",25000),
                new Intern(2,"Samiksha","BA",20000),
                new Intern(3,"Deepa","BA",20000),
                new Intern(4,"Sahil","BA",20000),
                new Intern(5,"Chaturved","Full Stack",25000)
        );
    }

}
