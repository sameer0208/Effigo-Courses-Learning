package com.effigopracticeproject.learning_portal.dto;

import com.effigopracticeproject.learning_portal.entity.RegisteredCourses;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class UserResponseDto {
    private String userId;
    private String username;
    private String userRole;
    private LocalDateTime registrationDateTime;
    private List<RegisteredCourses> registeredCourses;
}
