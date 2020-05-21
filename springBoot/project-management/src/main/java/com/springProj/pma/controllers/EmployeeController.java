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
import com.springProj.pma.services.EmployeeService;

// @Controller annotation도 spring이 automatically scan한다

@Controller
@RequestMapping("/employees")
public class EmployeeController 
{
	@Autowired
	EmployeeService empService;
	// EmployeeRepository가 아닌 EmployeeService를 사용하는 이유???
	// DB와 연결할 때 문제가 생겼으면, service code만 고치면 된다. 
	// Controller는 DB연결과는 상관 없는 부분으로 만들기 위하여
	
	
	// 1 // Field Injection
//	@Autowired
//	EmployeeRepository empRepo;
	
	
	
	// 2 //	 Construct Injection
	// Constructor injection을 위해서는 constructor를 만들고 
	// 위의 Autowired annotation 부분이 없어지면 된다
//	public EmployeeController(EmployeeRepository empRepo) 
//	{
//		this.empRepo = empRepo;
//	}
	
	
	
	// 3 // Setter Injection
	// setter injection은  setter를 만들고
	// setter에 autowired가 필요하다
	// EmployeeRepository variable에는 Autowired annotation이 필요없다
//	EmployeeRepository empRepo;
//	
//	@Autowired
//	public void setEmpRepo(EmployeeRepository empRepo)
//	{
//		this.empRepo = empRepo;
//	}
	
	
	
	
	
	@GetMapping
	public String listEmployee(Model model)
	{
		Iterable<Employee> list = empService.getAll();
		model.addAttribute("listEmp", list);
		
//		List<Employee> list = empRepo.findAll();
//		model.addAttribute("listEmp", list);
		
		return "employees/list-employee";		
	}
	
	
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
		empService.save(emp);
		
		
		// save to the database using an employee crud repository
		//empRepo.save(emp);
		
		return "redirect:/employees";
	}

}
