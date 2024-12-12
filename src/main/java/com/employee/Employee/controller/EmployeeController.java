package com.employee.Employee.controller;


import com.employee.Employee.dto.EmployeeRequestDTO;
import com.employee.Employee.dto.EmployeeResponseDTO;
import com.employee.Employee.exception.EmptyEmployeeListException;
import com.employee.Employee.exception.NotFoundException;
import com.employee.Employee.service.EmployeeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    private EmployeeServices employeeServices;

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/v1/getAllEmployee")
    public ResponseEntity<EmployeeResponseDTO> getAllEmployeeData() throws EmptyEmployeeListException {
        return employeeServices.getAllEmployeeDetail();
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/v1/getEmployeeById")
    public ResponseEntity<EmployeeResponseDTO> getOneEmployeeById(@RequestHeader("id") Long id) throws NotFoundException {
        return employeeServices.getEmployeeById(id);
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PostMapping("/v1/createEmployee")
    public ResponseEntity<EmployeeResponseDTO> createEmployee(@RequestBody EmployeeRequestDTO employeeRequestDTO) {
        return employeeServices.addEmployee(employeeRequestDTO);
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PutMapping("/v1/updateEmployee")
    public ResponseEntity<EmployeeResponseDTO> updateEmployee(@RequestBody EmployeeRequestDTO updatedEmployee, @RequestHeader("id") Long id) throws NotFoundException {
        return employeeServices.updateEmployee(updatedEmployee, id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/v1/deleteEmployee")
    public ResponseEntity<EmployeeResponseDTO> deleteEmployee(@RequestParam("id") Long id) throws NotFoundException {
        return employeeServices.deleteEmployee(id);
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/v1/getFirstThreeEmployee")
    public ResponseEntity<EmployeeResponseDTO> getFirstThreeEmployee() throws EmptyEmployeeListException {
        return employeeServices.getFirstThreeEmployee();
    }

}
