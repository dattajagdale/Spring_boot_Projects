package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
	Employee findEmployeeByEmailAndContact(String email, String contact);
	
	
	
	 Employee findEmployeeByEmail(String email);
	 
	 Employee findEmployeeByIdAndEmail(long id, String email);
	
	
	
	
	
	

}
