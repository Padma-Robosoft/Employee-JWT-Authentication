package com.employee.Employee;

import com.employee.Employee.model.Employee;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import java.util.ArrayList;
import java.util.List;

@EnableCaching
@SpringBootApplication
public class EmployeeApplication {

	public static List<Employee> employeeList=new ArrayList<>();

	public static void main(String[] args) {
		SpringApplication.run(EmployeeApplication.class, args);
	}

}
