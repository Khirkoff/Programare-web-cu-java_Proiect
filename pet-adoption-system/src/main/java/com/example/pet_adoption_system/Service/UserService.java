package com.example.pet_adoption_system.Service;

import com.example.pet_adoption_system.Constants.CurrentUser;
import com.example.pet_adoption_system.Constants.UserRole;
import com.example.pet_adoption_system.RequestBody.LoginBody;
import com.example.pet_adoption_system.RequestBody.RegistrationBody;
import com.example.pet_adoption_system.Model.User;
import com.example.pet_adoption_system.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Register a new user
    public User registerUser(RegistrationBody registrationBody) {
        Optional<User> existingEmail = userRepository.findByEmail(registrationBody.getEmail());
        Optional<User> existingUsername = userRepository.findByUsername(registrationBody.getUsername());

        if (existingEmail.isPresent()) {
            throw new IllegalArgumentException("Email is already in use!");
        }
        if (existingUsername.isPresent()) {
            throw new IllegalArgumentException("Username is already in use!");
        }

        User user = new User();
        user.setUsername(registrationBody.getUsername());
        user.setEmail(registrationBody.getEmail());
        user.setPassword(registrationBody.getPassword());
        user.setRole(UserRole.CLIENT); // Using the constant

        userRepository.save(user);
        return user;
    }

    public String loginUser(LoginBody loginBody) {
        Optional<User> existingUsername = userRepository.findByUsername(loginBody.getUsername());

        if (existingUsername.isPresent()) {
            User user = existingUsername.get();
            if (loginBody.getPassword().equals(user.getPassword())) {
                CurrentUser currentUser = new CurrentUser();
                currentUser.id=user.getId();
                currentUser.role=user.getRole();
                return "Login successful!";
            } else {
                throw new IllegalArgumentException("Invalid password!");
            }
        } else {
            throw new IllegalArgumentException("User not found!");
        }
    }
}


