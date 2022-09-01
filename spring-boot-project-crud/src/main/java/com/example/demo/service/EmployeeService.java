package com.example.demo.service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.example.demo.model.Employee;

public interface EmployeeService {
	
	List<Employee> getAllEmployees();
	
	void saveEmployee(Employee employee);
	
	Employee getEmployeeById(long id);
	
	void deleteEmployeeById(long id);

	Employee signIn(String email, String contact);
	
	
	Employee findEmployee(String email);
	
	Employee findEmployeeByIdAndEmail(long id, String email);
	
	
	
	

}
