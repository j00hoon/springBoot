package com.springProj.pma.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.springProj.pma.dto.ProjectStage;
import com.springProj.pma.dto.TimeChartData;
import com.springProj.pma.entity.Employee;
import com.springProj.pma.entity.Project;

public interface ProjectRepository extends PagingAndSortingRepository<Project, Long>
{
	
//	@Override
//	public List<Project> findAll();
	
	@Query(nativeQuery=true, value="select project_stage as label, count(*) as value " + 
			"from project " + 
			"group by project_stage")
	public List<ProjectStage> projectStage();
	
	
	@Query(nativeQuery=true, value="select * from project where project_id = ?1")
	public Project findByProjectId(long id);


	
	@Query(nativeQuery = true, value="select project_name as projectName, start_date as startDate, end_date as endDate from project where start_date is not null")
	public List<TimeChartData> getProjectTimeline();
	
	
}
