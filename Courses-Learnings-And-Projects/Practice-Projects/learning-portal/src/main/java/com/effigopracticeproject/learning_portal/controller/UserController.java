package com.effigopracticeproject.learning_portal.controller;

import com.effigopracticeproject.learning_portal.dto.UserRequestDto;
import com.effigopracticeproject.learning_portal.dto.UserResponseDto;
import com.effigopracticeproject.learning_portal.entity.RegisteredCourses;
import com.effigopracticeproject.learning_portal.entity.User;
import com.effigopracticeproject.learning_portal.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/learning-portal/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping("/create-user")
    public User createUser(@RequestBody UserRequestDto userRequestDto)
    {
        logger.info("Creating a new user with username: {}",userRequestDto.getUsername());
        User newUser =  userService.createUser(
                userRequestDto.getUsername(),
                userRequestDto.getPassword(),
                userRequestDto.getUserRole()
        );
        logger.info("User created successfull with ID: {}", newUser.getUserId());
        return newUser;
    }

    @GetMapping("/fetch-user/{id}")
    public UserResponseDto getUserById(@PathVariable("id") String userId)
    {
        logger.info("Fetching user with ID: {}", userId);
        UserResponseDto userResponseDto = userService.getUserById(userId);
        if(userResponseDto==null)
        {
            logger.warn("No user found with ID: {}", userId);
        }
        else
        {
            logger.info("User fetched successfully with ID: {}", userId);
        }
        return userResponseDto;
    }

    @GetMapping("/fetch-user-registered-course/{id}")
    public List<RegisteredCourses> getRegisteredCoursesOfUser(@PathVariable("id") String userId)
    {
        logger.info("Fetching registered courses for user ID: {}", userId);
        return userService.getRegisteredCoursesOfUser(userId);
    }

    @DeleteMapping("delete-user/{id}")
    public void deleteUserById(@PathVariable("id") String userId)
    {
        logger.info("Deleting user with ID: {}", userId);
        userService.deleteUserById(userId);
        logger.info("User deleted successfully with ID: {}", userId);
    }

    @GetMapping("/fetch-all-users")
    public List<User> getAllUsers()
    {
        logger.info("Fetching all users");
        List<User> users = userService.getAllUsers();
        logger.info("Fetched {} users successfully", users.size());
        return users;
    }

    @PutMapping("/update/user-details/{id}")
    public User updateUserById(@PathVariable("id") String userId, @RequestBody User updateUserDetails)
    {
        logger.info("Updating user with ID: {}", userId);
        User updatedUser = userService.updateUserById(userId, updateUserDetails);
        logger.info("User updated successfully with ID: {}", userId);
        return updatedUser;
    }
}
