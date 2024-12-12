package com.employee.Employee.dto;

import lombok.Getter;

@Getter
public class UserResponseDTO {
    private int status;
    private int code;
    private String message;
    private UserResponse data;

    public UserResponseDTO(int status, int code, String message, UserResponse userResponse) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.data = userResponse;
    }
}

