package com.effigopracticeproject.learning_portal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisteredCoursesRequestDto {
    private String userId;
    private String courseId;
}
