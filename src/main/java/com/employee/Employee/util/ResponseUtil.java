package com.employee.Employee.util;

import com.employee.Employee.dto.EmployeeResponse;
import com.employee.Employee.dto.EmployeeResponseDTO;
import com.employee.Employee.model.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

public class ResponseUtil {

    public static ResponseEntity<EmployeeResponseDTO> successResponse(ArrayList<Employee> employeeResponseList) {
        return new ResponseEntity<>(new EmployeeResponseDTO(0, 200, "Success", new EmployeeResponse(employeeResponseList)), HttpStatus.OK);
    }

    public static ResponseEntity<EmployeeResponseDTO> createdResponse(ArrayList<Employee> employeeResponseList, String message) {
        return new ResponseEntity<>(new EmployeeResponseDTO(0, 200, message, new EmployeeResponse(employeeResponseList)), HttpStatus.CREATED);
    }

    public static ResponseEntity<EmployeeResponseDTO> errorResponse(int code, String errorMessage) {
        return new ResponseEntity<>(new EmployeeResponseDTO(-1, code, errorMessage, null), HttpStatus.valueOf(code));
    }

    public static ResponseEntity<EmployeeResponseDTO> errorResponseDefault(String errorMessage) {
        return new ResponseEntity<>(new EmployeeResponseDTO(-1, 404, errorMessage, null), HttpStatus.NOT_FOUND);
    }
}
