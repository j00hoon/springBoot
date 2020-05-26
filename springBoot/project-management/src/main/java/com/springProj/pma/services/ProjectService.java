package com.springProj.pma.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springProj.pma.dao.ProjectRepository;
import com.springProj.pma.dto.ProjectStage;
import com.springProj.pma.dto.TimeChartData;
import com.springProj.pma.entity.Project;

@Service
public class ProjectService 
{
	// ProjectService도 EmployeeService처럼 
	// ProjectRepository를 갖고 오는 방법이 세 가지이다
	
	@Autowired
	ProjectRepository proRepo;

	
	
	public Iterable<Project> getAll() 
	{
		return proRepo.findAll();
	}

	public Project save(Project proj) 
	{
		return proRepo.save(proj);
	}
	
	public List<ProjectStage> getProjectStage()
	{
		return proRepo.projectStage();
	}
	
	public Project findByProjectId(long id)
	{
		return proRepo.findByProjectId(id);
	}
	
	public void deleteByProjectId(long id)
	{
		proRepo.deleteById(id);
	}
	
	public List<TimeChartData> getProjectTimeline()
	{
		return proRepo.getProjectTimeline();
	}
	
	
}
