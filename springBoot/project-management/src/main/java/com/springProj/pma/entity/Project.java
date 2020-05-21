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
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

// 이 class structure는 db의 table structure와 같다
@Entity
public class Project 
{
	// @Id의 뜻은 application에게 projectId가 unique한 identifier라는 것을 알려주는 annotation
//	@GeneratedValue(strategy = GenerationType.AUTO)
	
	@Id
	@SequenceGenerator(name="project_seq_gen", sequenceName = "project_seq", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="project_seq_gen")
	private long project_id;
	
	private String project_name;
	
	private String project_stage; // NONSTARTED, COMPLETED, INPROGRESS
	
	private String project_desc;
	
	
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
	joinColumns=@JoinColumn(name="projectId"), 
	inverseJoinColumns=@JoinColumn(name="employeeId"))
	@JsonIgnore
	private List<Employee> employee;
	
	
	public Project() {}
		
	// The constructor has no projectId variable. Why?
	// Want to make DB generates id number automatically  
	public Project(String project_name, String project_stage, String project_desc) {
		super();
		this.project_name = project_name;
		this.project_stage = project_stage;
		this.project_desc = project_desc;
	}
	
	
	
	public List<Employee> getEmployee() {
		return employee;
	}

	

	public void setEmployee(List<Employee> employee) {
		this.employee = employee;
	}

	public long getProjectId() {
		return project_id;
	}
	public void setProjectId(long project_id) {
		this.project_id = project_id;
	}

	public String getProject_name() {
		return project_name;
	}

	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}

	public String getProject_stage() {
		return project_stage;
	}

	public void setProject_stage(String project_stage) {
		this.project_stage = project_stage;
	}

	public String getProject_desc() {
		return project_desc;
	}

	public void setProject_desc(String project_desc) {
		this.project_desc = project_desc;
	}
	
	

	
}
