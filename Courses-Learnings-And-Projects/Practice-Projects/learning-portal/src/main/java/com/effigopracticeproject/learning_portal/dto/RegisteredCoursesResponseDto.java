package com.effigopracticeproject.learning_portal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisteredCoursesResponseDto {
    private String registrationId;
    private String userId;
    private String username; // ✅ Added
    private String courseId;
    private String courseTitle; // ✅ Added
}
