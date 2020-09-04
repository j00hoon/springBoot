package com.sb.pma.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sb.pma.dao.EmployeeRepository;
import com.sb.pma.dao.ProjectRepository;
import com.sb.pma.entities.Employee;
import com.sb.pma.entities.Project;

@Controller
@RequestMapping("/projects")
public class ProjectController 
{
	@Autowired
	ProjectRepository proRepo;
	
	@Autowired
	EmployeeRepository empRepo;
	
	@GetMapping("/new")
	public String displayProjectForm(Model model)
	{
		Project aProject = new Project();
		List<Employee> employees = empRepo.findAll();
		
		model.addAttribute("proj", aProject);
		model.addAttribute("allEmployees", employees);
		
		return "projects/new-project";
	}
	
	@PostMapping("/save")
	public String createProject(Project proj, Model model)
	{
		// handle saving to the DB
		proRepo.save(proj);
		
				
		// use a redirect to prevent duplicate submission
		return "redirect:/projects";		
	}
	
	@GetMapping
	public String displayProjectList(Model model)
	{
		List<Project> projList = proRepo.findAll();
		
		model.addAttribute("projectsList", projList);
		
		return "projects/list-project";
	}
	
}
