package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;

@Controller
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@GetMapping("/")
	public ModelAndView loginGET(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		if (session.getAttribute("login") == null) {
			modelAndView.setViewName("login");
		} else {
			modelAndView.setViewName("login");
		}
		return modelAndView;
	}
	
	@GetMapping("/login")
	public ModelAndView loginGETMethod(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		if (session.getAttribute("login") == null) {
			modelAndView.setViewName("login");
		} else {
			modelAndView.setViewName("login");
		}
		return modelAndView;
	}

	@PostMapping("/login")
	public ModelAndView loginPOST(@RequestParam("email") String email, @RequestParam("password") String contact,
			HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		Employee employee = employeeService.signIn(email, contact);
		if (employee == null) {
			String message = "Incorrect credentials";
			modelAndView.addObject("message", message);
			modelAndView.setViewName("login");
		} else {
			session.setAttribute("email", email);
			modelAndView.setViewName("index");
		}
		return modelAndView;
	}
	
	@GetMapping("/logout")
	public ModelAndView signOut(HttpSession session) {
		session.invalidate();
		
		return new ModelAndView("redirect:/login");
	}
	
	@GetMapping("/fetchEmployeeData") 
	public String viewHomePage(Model model, HttpSession session) {
		
		if(session.getAttribute("email")==null) {
			return "login";
		}
		else {
			
			model.addAttribute("listEmployees", employeeService.getAllEmployees());
			  
			  return "index";
			
		}

	  }
	 

	@GetMapping("/showNewEmployeeForm")
	public String showNewEmployeeForm(Model model,HttpSession session) {
		if(session.getAttribute("email")==null) {
			return "login";
		}
		else {
			// create model attribute to bind form data
			Employee employee = new Employee();
			model.addAttribute("employee", employee);
			return "new_employee";
		}
		
		

	}

	@PostMapping("/saveEmployee")
	public ModelAndView saveEmployee(@ModelAttribute("employee") Employee employee, HttpSession session) {
		ModelAndView modelAndView=new ModelAndView();
		if(session.getAttribute("email")==null) {
			modelAndView.setViewName("login");
			return modelAndView;
		}
		else {
			
			String emailID=employee.getEmail();
			/* List<Employee> e2=employeeService.getAllEmployees(); */
			
			/*
			 * if(e2.contains(emailID)) {
			 * 
			 * String msg="email aleardy Exists Please Use Another Email Id"; return
			 * "index?msg="+msg; }else { // save employee data to database
			 * employeeService.saveEmployee(employee); return "index";
			 */
			Employee em=employeeService.findEmployee(emailID);
			System.out.println("hello value is :"+em);
			if(em!=null) {
				String msg="Email Aleardy Exists Please Use Another Email Id"; 
				
				modelAndView.addObject("message", msg);
				modelAndView.setViewName("new_employee");
				return modelAndView;
			}
			else {
				employeeService.saveEmployee(employee); 
				modelAndView.setViewName("index");
				return modelAndView;
			}
		}
			
		
						

	}
	
	@PostMapping("/saveUpdatedEmployee")
	public ModelAndView saveUpdatedEmployee(@ModelAttribute("employee") Employee employee, HttpSession session) {
		ModelAndView modelAndView=new ModelAndView();
		if(session.getAttribute("email")==null) {
			modelAndView.setViewName("login");
			return modelAndView;
		}
		else {
			
			String email1=employee.getEmail();
			long id=employee.getId();
			/* List<Employee> e2=employeeService.getAllEmployees(); */
			
			/*
			 * if(e2.contains(emailID)) {
			 * 
			 * String msg="email aleardy Exists Please Use Another Email Id"; return
			 * "index?msg="+msg; }else { // save employee data to database
			 * employeeService.saveEmployee(employee); return "index";
			 */
			//String email2=employeeService.findEmployeeByIdAndEmail(email1,id);
			Employee em=employeeService.findEmployeeByIdAndEmail(id,email1);
			//System.out.println("hello value is :"+em);
			if(em==null) {
				String msg="You can't update your email"; 
				modelAndView.addObject("message", msg);
				modelAndView.setViewName("update_employee");
				return modelAndView;
			}
			else {
				employeeService.saveEmployee(employee); 
				modelAndView.setViewName("index");
				return modelAndView;
			}
		}
			
		
						

	}

	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable(value = "id") long id, Model model,HttpSession session) {
		if(session.getAttribute("email")==null) {
			return "login";
		}
		else {
			// get employee from service
			Employee employee = employeeService.getEmployeeById(id);

			// set employee as model attribute
			model.addAttribute("employee", employee);

			return "/update_employee";
		}
		

	}
	
	

	@GetMapping("/deleteEmployee/{id}")
	public String deleteEmployee(@PathVariable(value = "id") long id, Model model,HttpSession session) {
		if(session.getAttribute("email")==null) {
			return "login";
		}
		else {
			this.employeeService.deleteEmployeeById(id);

			return "index";
		}
		
		

	}
	

}
