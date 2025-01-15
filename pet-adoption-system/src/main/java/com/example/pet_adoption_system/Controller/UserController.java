package com.example.pet_adoption_system.Controller;

import com.example.pet_adoption_system.RequestBody.LoginBody;
import com.example.pet_adoption_system.RequestBody.RegistrationBody;
import com.example.pet_adoption_system.Model.User;
import com.example.pet_adoption_system.Service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Tag(name = "User", description = "Register and login a user.")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    @Operation(summary = "Register a user.")
    public ResponseEntity<User> registerUser(@RequestBody RegistrationBody registrationBody) {
        try {
            User user = userService.registerUser(registrationBody);
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PostMapping("/login")
    @Operation(summary = "Login a user.")
    public ResponseEntity<String> loginUser(@RequestBody LoginBody loginBody) {
        try {
            String response = userService.loginUser(loginBody);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
