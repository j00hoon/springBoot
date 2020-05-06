package com.springProj.pma.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.springProj.pma.entity.Project;

public interface ProjectRepository extends CrudRepository<Project, Long>
{
	@Override
	public List<Project> findAll();
}
