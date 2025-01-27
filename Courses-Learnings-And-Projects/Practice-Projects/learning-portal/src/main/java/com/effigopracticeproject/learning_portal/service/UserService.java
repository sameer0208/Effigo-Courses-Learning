package com.effigopracticeproject.learning_portal.service;


import com.effigopracticeproject.learning_portal.dto.UserRequestDto;
import com.effigopracticeproject.learning_portal.dto.UserResponseDto;
import com.effigopracticeproject.learning_portal.entity.RegisteredCourses;
import com.effigopracticeproject.learning_portal.entity.User;
import com.effigopracticeproject.learning_portal.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    public User createUser(String username, String password, String userRole) {
        logger.info("Creating a new user with username: {}", username);

        User user = new User();
        user.setUserName(username);
        user.setPassword(password);
        user.setUserRole(userRole);
        user.setRegistrationDateTime(LocalDateTime.now());

        User savedUser = userRepository.save(user);
        logger.info("User saved successfully with ID: {}", savedUser.getUserId());
        return savedUser;
    }

    public UserResponseDto getUserById(String userId) {
        logger.info("Fetching user with ID: {}", userId);

        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            logger.warn("No user found with ID: {}", userId);
            return null;
        }
        logger.info("User fetched successfully with ID: {}", userId);
        return new UserResponseDto(user.getUserId(), user.getUserName(), user.getUserRole(), user.getRegistrationDateTime(), user.getRegisteredCourses());
    }

    public void deleteUserById(String userId) {
        logger.info("Deleting user with ID: {}", userId);
        userRepository.deleteById(userId);
        logger.info("User deleted successfully with ID: {}", userId);
    }

    public List<User> getAllUsers() {
        logger.info("Fetching all users");
        return userRepository.findAll();
    }

    public User updateUserById(String userId, User updatedUserDetails) {
        logger.info("Updating user with ID: {}", userId);

        User userToUpdate = userRepository.findById(userId).orElse(null);
        if (userToUpdate == null) {
            logger.warn("No user found with ID: {}", userId);
            return null;
        }

        userToUpdate.setUserName(updatedUserDetails.getUserName());
        userToUpdate.setPassword(updatedUserDetails.getPassword());
        userToUpdate.setUserRole(updatedUserDetails.getUserRole());

        User updatedUser = userRepository.save(userToUpdate);
        logger.info("User updated successfully with ID: {}", updatedUser.getUserId());
        return updatedUser;
    }

    public List<RegisteredCourses> getRegisteredCoursesOfUser(String userId) {
        logger.info("Fetching registered courses for user ID: {}", userId);
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            logger.warn("No user found with ID: {}", userId);
            return null;
        }
        return user.getRegisteredCourses();
    }


}
