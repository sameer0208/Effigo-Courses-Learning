package com.effigopracticeproject.learning_portal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FavouriteCourseRequestDto {
    private RegisteredCoursesDto registeredCourses;
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class RegisteredCoursesDto {
    private String registrationId;
}
