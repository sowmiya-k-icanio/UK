/*
 * Copyright (c) Icanio
 */

package com.example.compass.services;

import com.example.compass.dao.User;
import com.example.compass.dto.UserDTO;
import com.example.compass.exception.CustomException;
import com.example.compass.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServices {

    private static final Logger LOG = LoggerFactory.getLogger(UserServices.class);

    private final UserRepository userRepository;

    public UserServices(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO createUsers(UserDTO userDTO) {
        // Validate the user input before processing
        validateUser(userDTO);

        // Create a new User object
        User saveUser = new User();
        saveUser.setFirstName(userDTO.getFirstName());
        saveUser.setLastName(userDTO.getLastName());
        saveUser.setCompanyName(userDTO.getCompanyName());
        saveUser.setAcceptTnc(userDTO.getAcceptTnc());
        saveUser.setEmail(userDTO.getEmail());

        // Save the user in the repository and convert to DTO before returning
        User user = userRepository.save(saveUser);
        return convertToDTO(user);
    }



    // Validate the user's email, checking for emptiness, existence, and format
    private void validateUser(UserDTO userDTO) {
        // Basic email validation before setting the email
        String email = userDTO.getEmail();
        if (email == null || email.trim().isEmpty()) {
            throw new CustomException("Email cannot be empty", HttpStatus.BAD_REQUEST);
        }

        // Check if the email already exists in the database
        if (userRepository.existsByEmail(email)) {
            throw new CustomException("Email Already Exists", HttpStatus.BAD_REQUEST);
        }

        // Additional validation for email format
        if (!isValidEmail(email)) {
            throw new CustomException("Invalid Email Format", HttpStatus.BAD_REQUEST);
        }
    }

    // Check if an email has a valid format using a basic regular expression
    private boolean isValidEmail(String email) {
        return email != null && email.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");
    }


    public UserDTO updateUsers(String id, UserDTO userDTO) {
        // Find the existing user by ID
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new CustomException("User not found", HttpStatus.NOT_FOUND));

        // Validate and update user fields
        validateAndUpdateUser(existingUser, userDTO);

        // Save the updated user to the repository
        User updatedUser = userRepository.save(existingUser);

        // Convert the updated user to DTO before returning
        return convertToDTO(updatedUser);
    }

    // Validate and update user fields based on the provided UserDTO
    private void validateAndUpdateUser(User existingUser, UserDTO userDTO) {
        // Update user fields from the DTO
        existingUser.setFirstName(userDTO.getFirstName());
        existingUser.setLastName(userDTO.getLastName());
        existingUser.setCompanyName(userDTO.getCompanyName());
        existingUser.setAcceptTnc(userDTO.getAcceptTnc());
        existingUser.setEmail(userDTO.getEmail());
    }

    public UserDTO getByUserId(String id) {
        // Find the user by ID and convert to DTO, or throw an exception if not found
        return userRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new CustomException("User not found", HttpStatus.NOT_FOUND));
    }

    // Convert User entity to UserDTO
    private UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setCompanyName(user.getCompanyName());
        userDTO.setAcceptTnc(user.getAcceptTnc());
        return userDTO;
    }

    public List<UserDTO> getByAllUser(int page, int size) {
        // Create a pageable object for pagination
        Pageable pageable = PageRequest.of(page - 1, size);

        // Fetch users with pagination from the repository
        Page<User> users = userRepository.findAll(pageable);

        // Convert the fetched users to a list of UserDTOs
        return users.getContent()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    public User getByAllUsers() {
        // Create a pageable object for pagination

        // Fetch users with pagination from the repository
        List<User> users = userRepository.findAll();
        if(users.isEmpty()){
            throw  new CustomException("user empty",HttpStatus.BAD_REQUEST);
        }
        return users.get(6);

    }

    public User deleteUser(String id) {
        // Verify if the user exists, and throw an exception if not found
        getByUserId(id);

        // Delete the user by ID
        userRepository.deleteById(id);

        // Throw an exception indicating successful deletion
        throw new CustomException("User deleted successfully", HttpStatus.OK);
    }

}
