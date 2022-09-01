package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.Employee;
import com.example.demo.model.Visitor;
import com.example.demo.service.VisitorService;

@Controller
public class VisitorController {
	
	@Autowired
	private VisitorService visitorService;
	
	@GetMapping(value = "/showVisitors") 
	public String showVisitors(Model model,HttpSession session) {
		if(session.getAttribute("email")==null){
			return "login";
		}
		else {
			model.addAttribute("listVisitors",visitorService.getAllVisitor());
			  
			  return "show_visitors";
		}
		
	  
	  
	  }
	
	@PostMapping("/saveUpdatedVisitors")
	public String saveUpdatedVisitor(@ModelAttribute("visitor") Visitor visitor, HttpSession session) {
		if(session.getAttribute("email")==null){
			return "login";
		}
		else {
			visitor.setUpdatedBy(session.getAttribute("email").toString());
			// save visitor data to database
			visitorService.saveVisitor(visitor);
			return "show_visitors";
		}
		

	}
	
	@PostMapping("/saveVisitors")
	public String saveVisitor(@ModelAttribute("visitor") Visitor visitor, HttpSession session) {
		if(session.getAttribute("email")==null){
			return "login";
		}
		else {
			visitor.setEntryBy(session.getAttribute("email").toString());
			// save visitor data to database
			visitorService.saveVisitor(visitor);
			return "show_visitors";
		}
		

	}
	
	
	@GetMapping("/showFormForUpdateVisitor/{id}")
	public String showFormForUpdateVisitor(@PathVariable(value = "id") long id, Model model, HttpSession session) {
		if(session.getAttribute("email")==null){
			return "login";
		}
		else {
			// get employee from service
			Visitor visitor = visitorService.getVisitorById(id);

			// set employee as model attribute
			model.addAttribute("visitor", visitor);

			return "/update_visitor";
		}
		

	}
	
	@GetMapping("/showNewVisitorForm")
	public String showNewVisitorForm(Model model,HttpSession session) {
		if(session.getAttribute("email")==null){
			return "login";
		}
		else {
			// create model attribute to bind form data
			Visitor visitor = new Visitor();
			model.addAttribute("visitor", visitor);
			return "new_visitor";
		}
		

	}
	
	
	

}
