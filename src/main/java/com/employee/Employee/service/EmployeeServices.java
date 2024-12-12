package com.employee.Employee.service;


import com.employee.Employee.dto.EmployeeRequestDTO;
import com.employee.Employee.dto.EmployeeResponseDTO;
import com.employee.Employee.exception.EmptyEmployeeListException;
import com.employee.Employee.exception.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface EmployeeServices {

    ResponseEntity<EmployeeResponseDTO> getAllEmployeeDetail() throws EmptyEmployeeListException;

    ResponseEntity<EmployeeResponseDTO> getEmployeeById(Long id) throws NotFoundException;

    ResponseEntity<EmployeeResponseDTO> addEmployee(EmployeeRequestDTO employeeRequestDTO);

    ResponseEntity<EmployeeResponseDTO> updateEmployee(EmployeeRequestDTO employee, Long id) throws NotFoundException;

    ResponseEntity<EmployeeResponseDTO> deleteEmployee(Long id) throws NotFoundException, NotFoundException;

    ResponseEntity<EmployeeResponseDTO> getFirstThreeEmployee() throws EmptyEmployeeListException;
}
