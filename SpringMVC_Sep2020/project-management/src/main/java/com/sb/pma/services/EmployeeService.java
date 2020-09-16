package com.sb.pma.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sb.pma.dao.EmployeeRepository;

@Service
public class EmployeeService 
{
	// Field Injection
	//@Autowired
	
	EmployeeRepository empRepo;
	
	
	
	// Constructor Injection
//	public EmployeeService(EmployeeRepository empRepo)
//	{
//		this.empRepo = empRepo;
//	}

	
	
	// Setter Injection
//	@Autowired
//	public void setEmpRepo(EmployeeRepository empRepo) 
//	{	
//		this.empRepo = empRepo;
//	}
	
	
	/////////////////////////////////////////////////////////////////
	
	
	
	// Field Injection
	// @Autowired
	// @Qualifier("staffRepo1")
	
	IStaffRepo staffRepo;
	
	
	// Constructor Injection
	// @Qualifer 첫 문자는 무조건 소문자!!!
	// The first letter must be lower case!!!
	public EmployeeService(@Qualifier("staffRepo1")IStaffRepo staffRepo)
	{
		this.staffRepo = staffRepo;
	}


	
	
	// Setter Injection
//	@Autowired
//	@Qualifier("staffRepo1")
//	public void setStaffRepo(IStaffRepo staffRepo) 
//	{
//		this.staffRepo = staffRepo;
//	}
	
	
	
	
}
