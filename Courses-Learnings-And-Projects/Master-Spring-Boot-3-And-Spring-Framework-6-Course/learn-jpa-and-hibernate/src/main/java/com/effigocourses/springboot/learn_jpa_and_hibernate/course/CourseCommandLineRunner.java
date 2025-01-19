package com.effigocourses.springboot.learn_jpa_and_hibernate.course;

import com.effigocourses.springboot.learn_jpa_and_hibernate.course.jpa.CourseJpaRepository;
import com.effigocourses.springboot.learn_jpa_and_hibernate.course.springdatajpa.CourseSpringDataJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CourseCommandLineRunner implements CommandLineRunner {

//    @Autowired
//    private CourseJdbcRepository repository;

//    @Autowired
//    private CourseJpaRepository repository;

    @Autowired
    private CourseSpringDataJpaRepository repository;

    @Override
    public void run(String... args) throws Exception {
            repository.save(new Course(1,"Maven Dependencies", "Udemy with Effigo"));
        repository.save(new Course(2,"Spring MVC", "Udemy with Bob e Procure"));
        repository.save(new Course(3,"Spring Boot", "Udemy with Effigo"));
        repository.deleteById(2l);
        System.out.println(repository.findById(3l));
        System.out.println(repository.findAll());
        System.out.println(repository.findByAuthor("Udemy with Effigo"));
    }
}
