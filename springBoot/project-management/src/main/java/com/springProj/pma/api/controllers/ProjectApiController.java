package com.springProj.pma.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.springProj.pma.dao.ProjectRepository;
import com.springProj.pma.entity.Project;

@RestController
@RequestMapping("/app-api/projects")
public class ProjectApiController 
{
	@Autowired
	ProjectRepository proRepo;
	
	
	
	@GetMapping
	public Iterable<Project> getProjects()
	{
		return proRepo.findAll();
	}
	
	
	
	@GetMapping("/{id}")
	public Project getProjectById(@PathVariable("id") long id)
	{
		return proRepo.findByProjectId(id);
	}
	
	
	@PostMapping(consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Project create(@RequestBody Project project)
	{
		return proRepo.save(project);
	}
	
	
	@PutMapping()
	@ResponseStatus(HttpStatus.OK)
	public Project update(@RequestBody Project project)
	{
		return proRepo.save(project);
	}
	
	
	
	@PatchMapping(path = "/{id}", consumes = "application/json")
	public Project partialupdate(@PathVariable("id") long id, @RequestBody Project patchPro)
	{
		Project pro = proRepo.findByProjectId(id);
		
		if(patchPro.getProject_name() != null)
		{
			pro.setProject_name(patchPro.getProject_name());
		}// if
		if(patchPro.getProject_stage() != null)
		{
			pro.setProject_stage(patchPro.getProject_stage());
		}// if
		if(patchPro.getProject_desc() != null)
		{
			pro.setProject_desc(patchPro.getProject_desc());
		}// if
		
		return proRepo.save(pro);
	}
	
	
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") long id)
	{
		try
		{
			proRepo.deleteById(id);
		}// try
		catch(EmptyResultDataAccessException e)
		{
			
		}// catch
	}
	
	
	
	
	
}
