package com.springProj.pma.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.springProj.pma.entity.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Long>
{
	@Override
	public List<Employee> findAll();
}
