package com.springProj.pma.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.springProj.pma.dao.EmployeeRepository;
import com.springProj.pma.dao.ProjectRepository;
import com.springProj.pma.entity.Employee;
import com.springProj.pma.entity.Project;

@Controller
public class HomeController 
{
	@Autowired
	ProjectRepository proRepo;
	
	@Autowired
	EmployeeRepository empRepo;
	
	@GetMapping("/")
	public String displayHome(Model model)
	{
		// querying the database for projects
		List<Project> projects = proRepo.findAll();
		model.addAttribute("projectsList", projects);

		// querying the database for employees
		List<Employee> employees = empRepo.findAll();
		model.addAttribute("employeesList", employees);
		
		return "home";
	}
}
