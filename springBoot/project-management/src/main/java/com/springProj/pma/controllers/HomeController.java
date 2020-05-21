package com.springProj.pma.controllers;

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
import com.springProj.pma.dto.EmployeeProject;
import com.springProj.pma.dto.ProjectStage;
import com.springProj.pma.entity.Project;
import com.springProj.pma.services.EmployeeService;
import com.springProj.pma.services.ProjectService;

@Controller
public class HomeController 
{
	// @Value annotation을 이용해서, application.properties에서 
	// 내가 만들어주었던 version=0.1.0 의 값을 이용할 수 있다.
	// ver variable에 0.1.0의 값이 들어간 상태
	// Ex, displayHome()을 보면
	@Value("${envVersion}")
	private String ver;
	
	@Autowired
	ProjectService proService;
	
	@Autowired
	EmployeeService empService;
	
	
//	@Autowired
//	ProjectRepository proRepo;
//	
//	@Autowired
//	EmployeeRepository empRepo;
	
	@GetMapping("/")
	public String displayHome(Model model) throws JsonProcessingException
	{
		// @Value annotation 값 사용하기
		model.addAttribute("envVersion", ver);
		
		
		Map<String, Object> map = new HashMap<>();
		
		// querying the database for projects -> projectName, projectStage
		//List<Project> projects = proRepo.findAll();
		Iterable<Project> projects = proService.getAll();
		model.addAttribute("projectList", projects);
		
		
		
		// Pie Chart //
		//List<ProjectStage> projectStageCnt = proRepo.projectStage();
		List<ProjectStage> projectStageCnt = proService.getProjectStage();
		
		// Lets convert projectStageCnt object into a json structure for use in javascript
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonString = objectMapper.writeValueAsString(projectStageCnt);
		// [["NOTSTARTED", 1], ["INPROGRESS", 3], ["COMPLETED", 2]]
		
		model.addAttribute("projectStageCnt", jsonString);
		
		
		
		
		

		// querying the database for employees -> firstName, lastName, email
		//List<Employee> employees = empRepo.findAll();

		// querying the database for employees -> firstName, lastName, projectCount
		//List<EmployeeProject> employeesProjectCnt = empRepo.employeeProjects();
		List<EmployeeProject> employeesProjectCnt = empService.employeeProjects();
		model.addAttribute("employeesListProjectCnt", employeesProjectCnt);
		
		return "main/home";
	}
}
