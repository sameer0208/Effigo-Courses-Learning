package com.effigopracticeproject.learning_portal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseResponseDto {
    private String courseId;
    private Long courseCategory;
    private String description;
    private Long price;
    private String title;
}
