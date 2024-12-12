package com.employee.Employee.controller;

import com.employee.Employee.dto.LoginResponse;
import com.employee.Employee.dto.UserRequest;
import com.employee.Employee.dto.UserResponseDTO;
import com.employee.Employee.exception.NotFoundException;
import com.employee.Employee.service.EmailService;
import com.employee.Employee.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Log4j2
public class UserController {


    private final AuthenticationManager authenticationManager;

    private static final Logger logger = LogManager.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    public UserController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/v1/register")
    public ResponseEntity<UserResponseDTO> registerUser(@RequestBody UserRequest userRequest) {
        return userService.registerUser(userRequest);
    }
    @PostMapping("/v1/login")
    public ResponseEntity<LoginResponse> userLogin(@RequestBody UserRequest userRequest) throws NotFoundException {
        LoginResponse loginResponse = userService.userLogin(userRequest);
        emailService.sendEmail(
                userRequest.getEmail(),
                "Login Successful",
                "You have successfully logged into your account."
        );

        return ResponseEntity.ok(loginResponse);

    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserRequest userRequest) {
        try {
            // Authenticate user with Spring Security AuthenticationManager
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userRequest.getEmail(),
                            userRequest.getPassword()
                    )
            );

            // Send email after successful login
            emailService.sendEmail(
                    userRequest.getEmail(),
                    "Login Successful",
                    "You have successfully logged into your account."
            );

            return ResponseEntity.ok("Login successful");
        } catch (AuthenticationException ex) {
            // Handle failed authentication
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
    }


    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user")
    public String userEndpoint() {
        return "Hello, User!";
    }


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public String adminEndpoint() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        logger.debug("Authenticated user roles: {}", authentication.getAuthorities());
        return "Hello, Admin!";

    }

}
