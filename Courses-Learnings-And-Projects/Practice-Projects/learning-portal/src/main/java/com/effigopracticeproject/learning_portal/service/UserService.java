package com.effigopracticeproject.learning_portal.service;


import com.effigopracticeproject.learning_portal.dto.UserRequestDto;
import com.effigopracticeproject.learning_portal.dto.UserResponseDto;
import com.effigopracticeproject.learning_portal.entity.RegisteredCourses;
import com.effigopracticeproject.learning_portal.entity.User;
import com.effigopracticeproject.learning_portal.exceptions.NoUserFoundException;
import com.effigopracticeproject.learning_portal.mapper.UserResponseDtoMapper;
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

    @Autowired
    private UserResponseDtoMapper userResponseDtoMapper;

//    UserService(UserResponseDtoMapper userResponseDtoMapper){
//        this.userResponseDtoMapper = userResponseDtoMapper;
//    }


    public User createUser(String username, String password, String userRole) {
        logger.info("Creating a new user with username: {}", username);

        try {
            User user = new User();
            user.setUserName(username);
            user.setPassword(password);
            user.setUserRole(userRole);
            user.setRegistrationDateTime(LocalDateTime.now());

            User savedUser = userRepository.save(user);
            logger.info("User saved successfully with ID:        {}", savedUser.getUserId());
            return savedUser;
        } catch (Exception e) {
            logger.error("Error creating user: {}", e.getMessage());
            throw new RuntimeException("Error creating user", e);
        }
    }

    public UserResponseDto getUserById(String userId) {
        logger.info("Fetching user with ID: {}", userId);
        try {
            Optional<User> optionalUser = userRepository.findById(userId);
            if (optionalUser.isEmpty()) {
                throw new NoUserFoundException("No User Found with given ID: " + userId);
            }

            User user = optionalUser.get();
            logger.info("User fetched successfully with ID: {}", userId);
            return new UserResponseDto(user.getUserId(), user.getUserName(), user.getUserRole(), user.getRegistrationDateTime(), user.getRegisteredCourses());

        } catch (NoUserFoundException e) {
            logger.warn(e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error while fetching user: {}", e.getMessage());
            throw new RuntimeException("Error fetching user details", e);
        }
    }

    public void deleteUserById(String userId) {
        logger.info("Deleting user with ID: {}", userId);
        try
        {
            if (!userRepository.existsById(userId)) {
                throw new NoUserFoundException("No User Found with given ID: " + userId);
            }
            userRepository.deleteById(userId);
            logger.info("User deleted successfully with ID: {}", userId);
        }
        catch (NoUserFoundException e)
        {
            logger.warn(e.getMessage());
            throw e;
        }
        catch (Exception e)
        {
            logger.error("Error deleting user: {}", e.getMessage());
            throw new RuntimeException("Error deleting user", e);
        }
    }

    public List<User> getAllUsers() {
        logger.info("Fetching all users");
        return userRepository.findAll();
    }

    public User updateUserById(String userId, User updatedUserDetails) {
        logger.info("Updating user with ID: {}", userId);

        try {
            Optional<User> optionalUser = userRepository.findById(userId);
            if (optionalUser.isEmpty()) {
                throw new NoUserFoundException("No User Found with given ID: " + userId);
            }

            User userToUpdate = optionalUser.get();
            userToUpdate.setUserName(updatedUserDetails.getUserName());
            userToUpdate.setPassword(updatedUserDetails.getPassword());
            userToUpdate.setUserRole(updatedUserDetails.getUserRole());

            User updatedUser = userRepository.save(userToUpdate);
            logger.info("User updated successfully with ID: {}", updatedUser.getUserId());
            return updatedUser;
        }
        catch (NoUserFoundException e)
        {
            logger.warn(e.getMessage());
            throw e;
        }
        catch (Exception e)
        {
            logger.error("Error updating user: {}", e.getMessage());
            throw new RuntimeException("Error updating user", e);
        }
    }

    public List<RegisteredCourses> getRegisteredCoursesOfUser(String userId) {
        logger.info("Fetching registered courses for user ID: {}", userId);
        try {
            Optional<User> optionalUser = userRepository.findById(userId);
            if (optionalUser.isEmpty()) {
                throw new NoUserFoundException("No User Found with given ID: " + userId);
            }
            return optionalUser.get().getRegisteredCourses();
        }
        catch (NoUserFoundException e)
        {
            logger.warn(e.getMessage());
            throw e;
        }
        catch (Exception e)
        {
            logger.error("Error fetching registered courses: {}", e.getMessage());
            throw new RuntimeException("Error fetching registered courses", e);
        }
    }


}