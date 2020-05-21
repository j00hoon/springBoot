package com.springProj.pma.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.springProj.pma.dto.EmployeeProject;
import com.springProj.pma.entity.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Long>
{
//	@Override
//	public List<Employee> findAll();
	
	@Query(nativeQuery=true, value="select e.first_name as FirstName, e.last_name as LastName, count(pe.employee_id) as ProjectCount " + 
			"from employee e left join project_employee pe " + 
			"ON pe.employee_id = e.employee_id " + 
			"group by e.first_name, e.last_name " + 
			"order by 3 desc;")
	public List<EmployeeProject> employeeProjects();
}
