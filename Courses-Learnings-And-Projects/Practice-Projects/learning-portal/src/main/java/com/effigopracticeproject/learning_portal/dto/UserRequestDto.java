package com.effigopracticeproject.learning_portal.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {
    private String userId;
    @JsonProperty("username")
    private String username;
    @JsonProperty("password")
    private String password;
    @JsonProperty("userRole")
    private String userRole;
    @JsonProperty("registrationDateTime")
    private LocalDateTime registrationDateTime;
//    private List<RegisteredCourses> registeredCourses;
}
