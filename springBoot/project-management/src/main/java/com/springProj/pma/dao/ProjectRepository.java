package com.springProj.pma.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.springProj.pma.dto.ProjectStage;
import com.springProj.pma.entity.Project;

public interface ProjectRepository extends CrudRepository<Project, Long>
{
//	@Override
//	public List<Project> findAll();
	
	@Query(nativeQuery=true, value="select project_stage as label, count(*) as value " + 
			"from project " + 
			"group by project_stage;")
	public List<ProjectStage> projectStage();
}
