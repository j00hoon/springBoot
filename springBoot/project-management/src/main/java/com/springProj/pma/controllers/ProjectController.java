package com.springProj.pma.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springProj.pma.dao.EmployeeRepository;
import com.springProj.pma.dao.ProjectRepository;
import com.springProj.pma.entity.Employee;
import com.springProj.pma.entity.Project;

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
		List<Employee> employee = empRepo.findAll();
		
		model.addAttribute("aProject", aProject);
		model.addAttribute("allEmployees", employee);
		
		return "projects/new-project";
	}
	

	// RequestParam은 url에서 오는 값을 get할 때 필요한데, 
	// 새로운 project를 만들 때, 선택하는 employee id를 db에 저장하기 위해서는 url로 넘어오는 employee들의 id 값이 필요하다
	// 그러므로, RequestParam으로 넘어오는 id값을 받기 위해서 필요함.
	// RequestParam 역시 Project-Employee가 OneToMany에서 필요함. 
	// 그러므로, 현재 ManyToMany이므로 필요없다.
	@PostMapping("/save")
	public String createProject(Project proj, /*@RequestParam List<Long> employee,*/ Model model)
	{
		// This method should handle saving to the database
		proRepo.save(proj);
		
		/////////////////////////////////////////////////////////
		// 이 부분은 우리가 Project와 Employee가 OneToMany relationship이었을 때, employee에 assign된 projectId를 
		// DB에 추가하기 위해서 필요했던 부분
		
		// url로 넘어오는 emplyoee Id값으로 해당하는 employee들을 모두 선택
//		Iterable<Employee> chosenEmployee = empRepo.findAllById(employee);
//		
//		// for each문으로 갖고온 employee entity 각각에 해당하는 project들을 setter로 넣어준다
//		for(Employee emp : chosenEmployee)
//		{
//			// setter 사용
//			emp.setProject(proj);
//			// setter로 넣어준 완성된 employee를 employee DB에 다시 save해주면 끝
//			empRepo.save(emp);
//		}// for
		/////////////////////////////////////////////////////////
		
		
		
		
		
		// Project-Employee ManyToMany relationship에서 따로 code 추가가 필요없는 이유는
		// hibernate는 smart해서 알아서 manage 해주고, join table에 넣어준다.
		// 우리는 그저 알맞은 annotation을 써주기만 하면 되는 것.
		
		
		// use a redirect to prevent duplicate submissions
		return "redirect:/projects";		
	}
	
	@GetMapping
	public String listProject(Model model)
	{
		List<Project> list = proRepo.findAll();
		model.addAttribute("listProj", list);
		
		return "projects/list-project";		
	}
	
}
