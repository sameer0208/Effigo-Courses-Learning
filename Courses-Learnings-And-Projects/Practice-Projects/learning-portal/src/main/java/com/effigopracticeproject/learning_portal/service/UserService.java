package com.effigopracticeproject.learning_portal.service;


import com.effigopracticeproject.learning_portal.dto.UserRequestDto;
import com.effigopracticeproject.learning_portal.dto.UserResponseDto;
import com.effigopracticeproject.learning_portal.entity.RegisteredCourses;
import com.effigopracticeproject.learning_portal.entity.User;
import com.effigopracticeproject.learning_portal.exceptions.NoUserFoundException;
import com.effigopracticeproject.learning_portal.mapper.UserRequestDtoMapper;
import com.effigopracticeproject.learning_portal.mapper.UserResponseDtoMapper;
import com.effigopracticeproject.learning_portal.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.security.NoSuchAlgorithmException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserResponseDtoMapper userResponseDtoMapper;

    @Autowired
    private UserRequestDtoMapper userRequestDtoMapper;

    private static final String AES_KEY = "MySuperSecretKey"; // Must be 16 bytes for AES-128

    // AES Encryption Method
    private String encryptPassword(String password) {
        try {
            SecretKey secretKey = new SecretKeySpec(AES_KEY.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptedBytes = cipher.doFinal(password.getBytes());
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            throw new RuntimeException("Error encrypting password", e);
        }
    }

    // AES Decryption Method
    private String decryptPassword(String encryptedPassword) {
        try {
            SecretKey secretKey = new SecretKeySpec(AES_KEY.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedPassword));
            return new String(decryptedBytes);
        } catch (Exception e) {
            throw new RuntimeException("Error decrypting password", e);
        }
    }

//    public UserResponseDto createUser(UserRequestDto userRequestDto) {
//        logger.info("Creating a new user with username: {}", userRequestDto.getUsername());
//
//        try {
//            User user = userRequestDtoMapper.userRequestDtoToEntity(userRequestDto);
//            user.setRegistrationDateTime(LocalDateTime.now());
//            User savedUser = userRepository.save(user);
//            logger.info("User saved successfully with ID:        {}", savedUser.getUserId());
//            return userResponseDtoMapper.userEntityToDto(savedUser);
//        } catch (Exception e) {
//            logger.error("Error creating user: {}", e.getMessage());
//            throw new RuntimeException("Error creating user", e);
//        }
//    }
public UserResponseDto createUser(UserRequestDto userRequestDto) {
    logger.info("Creating a new user with username: {}", userRequestDto.getUsername());

    try {
        User user = userRequestDtoMapper.userRequestDtoToEntity(userRequestDto);

        // Encrypt the password before saving
        user.setPassword(encryptPassword(userRequestDto.getPassword()));

        user.setRegistrationDateTime(LocalDateTime.now());
        User savedUser = userRepository.save(user);
        logger.info("User saved successfully with ID: {}", savedUser.getUserId());

        return userResponseDtoMapper.userEntityToDto(savedUser);
    } catch (Exception e) {
        logger.error("Error creating user: {}", e.getMessage());
        throw new RuntimeException("Error creating user", e);
    }
}

   /* public UserResponseDto getUserById(String userId) {
        logger.info("Fetching user with ID: {}", userId);
        try {
            Optional<User> optionalUser = userRepository.findById(userId);
            if (optionalUser.isEmpty()) {
                throw new NoUserFoundException("No User Found with given ID: " + userId);
            }
//
//            User user = optionalUser.get();
            logger.info("User fetched successfully with ID: {}", userId);
//            return new UserResponseDto(user.getUserId(), user.getUserName(), user.getUserRole(), user.getRegistrationDateTime(), user.getRegisteredCourses());
            return optionalUser.map(userResponseDtoMapper::userEntityToDto).orElse(null);
        } catch (NoUserFoundException e) {
            logger.warn(e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error while fetching user: {}", e.getMessage());
            throw new RuntimeException("Error fetching user details", e);
        }
    }*/

    public UserResponseDto getUserById(String userId) {
        logger.info("Fetching user with ID: {}", userId);

        try {
            Optional<User> optionalUser = userRepository.findById(userId);
            if (optionalUser.isEmpty()) {
                throw new NoUserFoundException("No User Found with given ID: " + userId);
            }

            User user = optionalUser.get();

            // Decrypt the password before returning (Optional: You may decide to exclude passwords)
            String decryptedPassword = decryptPassword(user.getPassword());

            return new UserResponseDto(
                    user.getUserId(),
                    user.getUsername(),
                    user.getUserRole(),
                    user.getRegistrationDateTime(),
                    user.getRegisteredCourses()
            );
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
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new NoUserFoundException("No User Found with given ID: " + userId));
            user.getRegisteredCourses().clear();
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

    public List<UserResponseDto> getAllUsers() {
        logger.info("Fetching all users");
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userResponseDtoMapper::userEntityToDto)
                .collect(Collectors.toList());
    }

    public UserResponseDto updateUserById(String userId, UserRequestDto userRequestDto) {
        logger.info("Updating user details for ID: {}", userId);

        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new NoUserFoundException("No user found with ID: " + userId));

        // Map request DTO to user entity
        User updatedUser = userRequestDtoMapper.userRequestDtoToEntity(userRequestDto);
        updatedUser.setUserId(userId); // Ensure the ID remains unchanged
        updatedUser.setRegistrationDateTime(existingUser.getRegistrationDateTime());
        // Save the updated user
        updatedUser = userRepository.save(updatedUser);

        // Convert updated entity to response DTO and return
        return userResponseDtoMapper.userEntityToDto(updatedUser);
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

    public List<User> getUsersByRole(String role) {
        return userRepository.findAllByRole(role);
    }

}