package com.effigopracticeproject.learning_portal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavouriteCourseDto {
    private String favouriteId;
    private String registeredCourseId;
    private String username;
    private String courseTitle;
}
