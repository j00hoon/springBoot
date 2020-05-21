package com.springProj.pma.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springProj.pma.dao.EmployeeRepository;
import com.springProj.pma.dto.EmployeeProject;
import com.springProj.pma.entity.Employee;

@Service
public class EmployeeService 
{
	// 1
	@Autowired
	EmployeeRepository empRepo;

	
	
	
	public Iterable<Employee> getAll() 
	{
		return empRepo.findAll();
	}

	public Employee save(Employee emp) 
	{
		return empRepo.save(emp);
	}

	public List<EmployeeProject> employeeProjects()
	{
		return empRepo.employeeProjects();
	}
	
	// 2
//	EmployeeRepository empRepo;
//	
//	public EmployeeService(EmployeeRepository empRepo) 
//	{
//		this.empRepo = empRepo;
//	}

	
	
	
	// 3
//	EmployeeRepository empRepo;
//	
//	@Autowired
//	public void setEmpRepo(EmployeeRepository empRepo) 
//	{
//		this.empRepo = empRepo;
//	}
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	
	
	
	
	
	// Test //////////////////////////////////////////////////////////////////////////////////////////////
	
	
	// 1 //
	
	// Constructor Injection을 사용하고, Bean이 2개 이상일 경우
	// staffRepository 1과 2가 있고, 각각 iStaffRepository interface를 implements한다
	// 밑에와 같은 상황에서라면, 우리가 원하는 것이 1인지 2인지 알 수가 없다.
	
//	@Autowired
//	iStaffRepository iStaffRepo;
//	
//	public EmployeeService(iStaffRepository iStaffRepo)
//	{
//		this.iStaffRepo = iStaffRepo;
//	}
	
	// 이런 상황에서는
	// 1이나 2 class에 @Primary annotation을 추가해서, 어떤 것이 Primary인지 알려주는 방법이 있다
	// 혹은
	// @Qualifier("staffRepositoryImpl1") annotation을 이용한다
//	public EmployeeService(@Qualifier("staffRepositoryImpl1")iStaffRepository iStaffRepo)
//	{
//		this.iStaffRepo = iStaffRepo;
//	} 
	
	
	
	
	
	
	
	
	
	
	
	
	// 2 //
	// Field Injection을 사용하고, Bean이 2개 이상일 경우
	// @Qualifier("staffRepositoryImpl1") annotation을 이용한다
	// 밑에와 같이 추가만 하면 된다
	
//	@Qualifier("staffRepositoryImpl1")
//	@Autowired
//	iStaffRepository iStaffRepo;
	
	
	
	
	
	
	
	
	// 3 //
	// Setter Injection을 사용하고, Bean이 2개 이상일 경우
	// @Qualifier("staffRepositoryImpl1") annotation을 이용한다
	// 밑에와 같이 추가만 하면 된다
	
//	@Qualifier("staffRepositoryImpl1")
//	@Autowired
//	public void setEmpRepo(iStaffRepository iStaffRepo) 
//	{
//		this.iStaffRepo = iStaffRepo;
//	}

	
}
