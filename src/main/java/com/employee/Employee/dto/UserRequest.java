package com.employee.Employee.dto;


import com.employee.Employee.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserRequest {
    private String email;
    private String password;
    private Role role;
}
