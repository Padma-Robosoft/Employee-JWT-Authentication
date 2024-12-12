package com.employee.Employee.dto;

import lombok.*;

@Builder
@Data
@Getter
@Setter
//@AllArgsConstructor
public class EmployeeRequestDTO {
    private String name;
    private String designation;
    private String department;

    public EmployeeRequestDTO(String name, String designation, String department) {
        this.name = name;
        this.designation = designation;
        this.department = department;
    }
}
