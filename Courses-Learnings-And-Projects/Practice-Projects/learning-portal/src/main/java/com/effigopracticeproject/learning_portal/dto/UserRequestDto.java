package com.effigopracticeproject.learning_portal.dto;

import com.effigopracticeproject.learning_portal.entity.RegisteredCourses;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {
    private String userId;
    private String username;
    private String password;
    private String userRole;
    private LocalDateTime registrationDateTime;
    private List<RegisteredCourses> registeredCourses;
}
