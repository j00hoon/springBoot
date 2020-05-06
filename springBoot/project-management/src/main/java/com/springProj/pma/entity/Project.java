package com.springProj.pma.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// 이 class structure는 db의 table structure와 같다
@Entity
public class Project 
{
	// @Id의 뜻은 application에게 projectId가 unique한 identifier라는 것을 알려주는 annotation
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long projectId;
	
	private String projectName;
	
	private String projectStage; // NONSTARTED, COMPLETED, INPROGRESS
	
	private String projectDesc;
	
	
	public Project() {}
		
	// The constructor has no projectId variable. Why?
	// Want to make DB generates id number automatically  
	public Project(String projectName, String projectStage, String projectDesc) 
	{
		super();
		this.projectName = projectName;
		this.projectStage = projectStage;
		this.projectDesc = projectDesc;
	}
	
	public long getProjectId() {
		return projectId;
	}
	public void setProjectId(long projectId) {
		this.projectId = projectId;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getProjectStage() {
		return projectStage;
	}
	public void setProjectStage(String projectStage) {
		this.projectStage = projectStage;
	}
	public String getProjectDesc() {
		return projectDesc;
	}
	public void setProjectDesc(String projectDesc) {
		this.projectDesc = projectDesc;
	}
	
	
}
