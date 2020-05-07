package com.springProj.pma.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springProj.pma.dao.EmployeeRepository;
import com.springProj.pma.entity.Employee;
import com.springProj.pma.entity.Project;

@Controller
@RequestMapping("/employees")
public class EmployeeController 
{
	@Autowired
	EmployeeRepository empRepo;
	
	@GetMapping("/new")
	public String displayEmployeeForm(Model model)
	{
		Employee aEmployee = new Employee();
		
		model.addAttribute("aEmployee", aEmployee);
		return "employees/new-employee";		
	}
	
	@PostMapping("/register")
	public String registerEmployee(Employee emp, Model model)
	{
		// save to the database using an employee crud repository
		empRepo.save(emp);
		
		return "redirect:/employees/new";
	}
	
	@GetMapping
	public String listEmployee(Model model)
	{
		List<Employee> list = empRepo.findAll();
		model.addAttribute("listEmp", list);
		
		return "employees/list-employee";		
	}

}
