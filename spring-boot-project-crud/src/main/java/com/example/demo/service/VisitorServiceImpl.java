package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Employee;
import com.example.demo.model.Visitor;
import com.example.demo.repository.VisitorRepository;


@Service 
public class VisitorServiceImpl implements VisitorService{
	
	@Autowired
	private VisitorRepository visitorRepository;

	@Override
	public List<Visitor> getAllVisitor(){
		return visitorRepository.findAll();
	}

	@Override
	public void saveVisitor(Visitor visitor) {
		this.visitorRepository.save(visitor);
		
	}

	@Override
	public Visitor getVisitorById(long id) {
		Optional<Visitor> optional= visitorRepository.findById(id);
		Visitor visitor=null;
		if(optional.isPresent()) {
			visitor=optional.get();
		}
		else {
			throw new RuntimeException("Employee not found for id ::"+id);
			
		}
		return visitor;

	}
	
	
	

}
