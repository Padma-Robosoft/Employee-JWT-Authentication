package com.employee.Employee.service;


import com.employee.Employee.dto.LoginResponse;
import com.employee.Employee.dto.UserRequest;
import com.employee.Employee.dto.UserResponse;
import com.employee.Employee.dto.UserResponseDTO;
import com.employee.Employee.exception.NotFoundException;
import com.employee.Employee.model.User;
import com.employee.Employee.repository.UserRepository;
import com.employee.Employee.util.JwtUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Value("${success.user.created}")
    private String userCreatedMessage;

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public ResponseEntity<UserResponseDTO> registerUser(UserRequest userRequest) {
        if (userRepository.findByEmail(userRequest.getEmail()).isPresent()) {
            return new ResponseEntity<>(new UserResponseDTO(-1, 409, "Email already Present", null), HttpStatusCode.valueOf(409));    //
        }
        User newUser = new User();
        newUser.setEmail(userRequest.getEmail());
        newUser.setPassword(passwordEncoder.encode(userRequest.getPassword()));

        newUser.setRole(userRequest.getRole());
        logger.info("User created successfully");

        userRepository.save(newUser);

        UserResponse response = new UserResponse(newUser.getEmail(), newUser.getRole());

        return new ResponseEntity<>(new UserResponseDTO(0, 200, "User created successfully", response), HttpStatusCode.valueOf(200));
    }



    @Override
    public LoginResponse userLogin(UserRequest userRequest) throws NotFoundException {

        User user = userRepository.findByEmail(userRequest.getEmail())
                .orElseThrow(() -> new NotFoundException("User not found with email: " + userRequest.getEmail()));

        if (!passwordEncoder.matches(userRequest.getPassword(), user.getPassword())) {
            throw new NotFoundException("Invalid credentials");
        }
        UserDetails userDetails = org.springframework.security.core.userdetails.User        .builder()        .username(user.getEmail())        .password(user.getPassword())        .roles(user.getRole().name())        .build();

        String jwtToken = jwtUtils.generateTokenFromUsername(userDetails);

        LoginResponse loginResponse = new LoginResponse(user.getEmail(), user.getRole(), jwtToken);

        return loginResponse;
    }
}






