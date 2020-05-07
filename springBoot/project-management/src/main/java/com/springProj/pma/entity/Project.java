package com.springProj.pma.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

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
	
	// One to Many relationship -> One Project can have Many Employees
//	@OneToMany(mappedBy="project")
//	private List<Employee> employee;
	
	// OneToMany에서 바꾸는 이유는 
	// 각 employee들이 여러개의 Project에 assign 가능하도록 하기 위하여 
	
	// JoinTable은 이제 ManyToMany relationship이므로, Project와 Employee table들을 Join하여 관리하기 위해서 
	// JoinColumn의 name은 Project table에서 사용할 FK의 이름
	// inverseJoinColumns은 2개의 table이 join하므로, 반대편 column의 정보를 적어주는 것?
	@ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST}, 
			fetch = FetchType.LAZY)
	@JoinTable(name="Project_Employee", 
	joinColumns=@JoinColumn(name="project_id"), 
	inverseJoinColumns=@JoinColumn(name="employee_id"))
	private List<Employee> employee;
	
	
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
	
	
	
	public List<Employee> getEmployee() {
		return employee;
	}

	public void setEmployee(List<Employee> employee) {
		this.employee = employee;
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
