package com.sb.pma.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sb.pma.dao.EmployeeRepository;
import com.sb.pma.dao.ProjectRepository;
import com.sb.pma.dto.EmployeeProject;
import com.sb.pma.dto.ProjectStatus;
import com.sb.pma.entities.Project;

@Controller
public class HomeController 
{
	@Value("${version}")
	private String ver;
	
	@Autowired
	ProjectRepository proRepo;
	@Autowired
	EmployeeRepository empRepo;
	
	@GetMapping("/")
	public String displayHome(Model model) throws JsonProcessingException
	{
		model.addAttribute("versionNum", ver);
		
		Map<String, Object> map = new HashMap<>();
		
		List<Project> projects = proRepo.findAll();
		model.addAttribute("projectsList", projects);
		
		//List<Employee> employees = empRepo.findAll();
		List<EmployeeProject> employeesProjectCnt = empRepo.employeeProjects();
		model.addAttribute("employeesListProhjectsCnt", employeesProjectCnt);
		
		List<ProjectStatus> projectStatus = proRepo.getProjectStatus();
		
		// Convert projectStatus object into a json structure use in js
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonString = objectMapper.writeValueAsString(projectStatus);
		
		model.addAttribute("projectStatusCnt", jsonString);
		
		
		return "main/home";
	}
}
