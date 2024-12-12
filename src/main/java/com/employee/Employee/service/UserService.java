package com.employee.Employee.service;

import com.employee.Employee.dto.LoginResponse;
import com.employee.Employee.dto.UserRequest;
import com.employee.Employee.dto.UserResponseDTO;
import com.employee.Employee.exception.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
   // ResponseEntity<UserResponseDTO> registerUser(UserRequest userRequest);
  //  ResponseEntity<UserResponseDTO> loginUser(UserRequest userRequest);
   public ResponseEntity<UserResponseDTO> registerUser(UserRequest userRequest);
   public LoginResponse userLogin(UserRequest userRequest) throws NotFoundException;
  // public LoginResponse userLogin(UserRequest userRequest) ;
}
