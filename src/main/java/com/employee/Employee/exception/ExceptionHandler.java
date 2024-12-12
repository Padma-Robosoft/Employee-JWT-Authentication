package com.employee.Employee.exception;

import com.employee.Employee.dto.EmployeeResponseDTO;
import com.employee.Employee.dto.UserResponseDTO;
import com.employee.Employee.util.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<EmployeeResponseDTO> handleNotFoundException(NotFoundException exception) {
        return ResponseUtil.errorResponseDefault(exception.getMessage());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {EmptyEmployeeListException.class})
    public ResponseEntity<EmployeeResponseDTO> handleEmptyEmployeeListException(EmptyEmployeeListException exception) {
        return ResponseUtil.errorResponseDefault(exception.getMessage());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleGenericException(Exception ex) {
        return new ResponseEntity<>(new UserResponseDTO(-1, 500, "Internal Server Error: " + ex.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
