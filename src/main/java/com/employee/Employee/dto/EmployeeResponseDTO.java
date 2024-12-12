package com.employee.Employee.dto;


import lombok.Getter;
import lombok.Setter;

//@Builder
//@Data
@Setter
@Getter
public class EmployeeResponseDTO {

    private int status;
    private int code;
    private String message;
    private EmployeeResponse data;

    public EmployeeResponseDTO(int status, int code, String message, EmployeeResponse employeeResponse) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.data = employeeResponse;
    }
}



