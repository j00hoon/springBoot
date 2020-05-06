package com.springProj.pma.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springProj.pma.dao.ProjectRepository;
import com.springProj.pma.entity.Project;

@Controller
@RequestMapping("/projects")
public class ProjectController 
{
	@Autowired
	ProjectRepository proRepo;
	
	@GetMapping("/new")
	public String displayProjectForm(Model model)
	{
		Project aProject = new Project();
		
		model.addAttribute("aProject", aProject);
		return "new-project";
	}
	
	@PostMapping("/save")
	public String createProject(Project proj, Model model)
	{
		// This method should handle saving to the database
		proRepo.save(proj);
		
		// use a redirect to prevent duplicate submissions
		return "redirect:/projects/new";		
	}
	
}
