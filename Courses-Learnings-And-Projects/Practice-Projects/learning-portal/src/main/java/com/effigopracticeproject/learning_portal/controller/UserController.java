package com.effigopracticeproject.learning_portal.controller;

import com.effigopracticeproject.learning_portal.dto.UserRequestDto;
import com.effigopracticeproject.learning_portal.dto.UserResponseDto;
import com.effigopracticeproject.learning_portal.entity.RegisteredCourses;
import com.effigopracticeproject.learning_portal.entity.User;
import com.effigopracticeproject.learning_portal.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/learning-portal/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping("/create-user")
    public ResponseEntity<User> createUser(@RequestBody UserRequestDto userRequestDto)
    {
        logger.info("Creating a new user with username: {}", userRequestDto.getUsername());
        try {
            User createdUser = userService.createUser(
                    userRequestDto.getUsername(),
                    userRequestDto.getPassword(),
                    userRequestDto.getUserRole());
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        }
        catch (Exception e) {
            logger.error("Error creating user: {}", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/fetch-user/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable("id") String userId)
    {
        logger.info("Fetching user with ID: {}", userId);
        try
        {
            UserResponseDto userResponse = userService.getUserById(userId);
            if (userResponse != null) {
                return new ResponseEntity<>(userResponse, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception e) {
            logger.error("Error fetching user: {}", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/fetch-user-registered-course/{id}")
    public ResponseEntity<List<RegisteredCourses>> getRegisteredCoursesOfUser(@PathVariable("id") String userId)
    {
        logger.info("Fetching registered courses for user ID: {}", userId);
        try {
            List<RegisteredCourses> courses = userService.getRegisteredCoursesOfUser(userId);
            if (courses.isEmpty()) {
                return new ResponseEntity<>(courses, HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(courses, HttpStatus.OK);
        }
        catch (Exception e) {
            logger.error("Error fetching registered courses: {}", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("delete-user/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable("id") String userId)
    {
        logger.info("Deleting user with ID: {}", userId);
        try {
            userService.deleteUserById(userId);
            logger.info("User deleted successfully with ID: {}", userId);
            return new ResponseEntity<>("User deleted successfully", HttpStatus.NO_CONTENT);
        }
        catch (Exception e)
        {
            logger.error("Error deleting user: {}", e.getMessage());
            return new ResponseEntity<>("Error deleting user", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/fetch-all-users")
    public ResponseEntity<List<User>> getAllUsers()
    {
        logger.info("Fetching all users");
        try
        {
            List<User> users = userService.getAllUsers();
            if (users.isEmpty()) {
                return new ResponseEntity<>(users, HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(users, HttpStatus.OK);
        }
        catch (Exception e) {
            logger.error("Error fetching users: {}", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/user-details/{id}")
    public ResponseEntity<User> updateUserById(@PathVariable("id") String userId, @RequestBody User updateUserDetails)
    {
        logger.info("Updating user with ID: {}", userId);
        try {
            User updatedUser = userService.updateUserById(userId, updateUserDetails);
            if (updatedUser != null) {
                return new ResponseEntity<>(updatedUser, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception e) {
            logger.error("Error updating user: {}", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
