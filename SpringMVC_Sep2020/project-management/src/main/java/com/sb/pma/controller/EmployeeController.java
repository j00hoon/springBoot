package com.sb.pma.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sb.pma.dao.EmployeeRepository;
import com.sb.pma.entities.Employee;

@Controller
@RequestMapping("/employees")
public class EmployeeController 
{
	// Field Injection
//	@Autowired
	EmployeeRepository empRepo;
	
	
	
	// Constructor Injection
	public EmployeeController(EmployeeRepository empRepo)
	{
		this.empRepo = empRepo;
	}
	
	
	// Setter Injection
//	@Autowired
//	public void setEmpRepo(EmployeeRepository empRepo) 
//	{
//		this.empRepo = empRepo;
//	}
	
	
	
	
	
	@GetMapping("/new")
	public String displayEmployeeForm(Model model)
	{
		Employee employee = new Employee();
		model.addAttribute("emp", employee);
		
		return "employees/new-employee";
	}
	
	

	@PostMapping("/save")
	public String createEmployee(Employee emp, Model model)
	{
		empRepo.save(emp);
		
		return "redirect:/employees/new";
	}
	
	@GetMapping
	public String displayEmployeeList(Model model)
	{
		List<Employee> empList = empRepo.findAll();
		
		model.addAttribute("employeesList", empList);
		
		return "employees/list-employee";
	}
}
