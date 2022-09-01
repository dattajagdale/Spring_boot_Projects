package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Employee;
import com.example.demo.model.Visitor;


public interface VisitorService {
	
	List<Visitor> getAllVisitor();
	
	void saveVisitor(Visitor visitor);
	Visitor getVisitorById(long id);
//	
//	void deleteVisitorById(long id);

	
}
