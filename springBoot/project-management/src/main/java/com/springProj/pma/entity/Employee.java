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

@Entity
public class Employee 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long employeeId;
	
	private String firstName;
	private String lastName;
	private String email;
	
	// JoinColumn의 name이 Employee table에서 FK로써 Project로부터 값을 reference하게 된다
	// name이 table column의 이름
	
	// ManyToOne의 의미는 Many Employee가 One Project와 relationship이 있다.
	
	// Project 변수의 이름 theProject는 Project.java에서 OneToMany mappedBy와 일치해야 한다.
	
	// CascadeType은 Parent Table에 따라 Child Table의 상황?상태? 도 변화시키는 속성.

	// FetchType은 application이 실행되었을 때, data가 memory로 load되는 방식을 결정한다. EAGER는 Project가 load될 때, 연관된 모든 Employee들의 data도 load한다. 
	// lazy는 Employee data가 필요할 때, 나중에 load 되는 방식. 
	// In general, always use LAZY
//	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST}, 
//			fetch = FetchType.LAZY)
//	@JoinColumn(name="project_id")
//	private Project project;
	
	
	// ManyToMany이므로 Project변수를 List로 만든다
	@ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST}, 
			fetch = FetchType.LAZY)
	@JoinTable(name="Project_Employee", 
	joinColumns=@JoinColumn(name="employee_id"), 
	inverseJoinColumns=@JoinColumn(name="project_id"))
	private List<Project> project;
	
	public Employee() {}
	
	public Employee(String firstName, String lastName, String email) 
	{
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}
	
	

	/*
	 * public Project getProject() { return project; }
	 * 
	 * public void setProject(Project project) { this.project = project; }
	 */
	
	
	public List<Project> getProject() {
		return project;
	}

	public void setProject(List<Project> project) {
		this.project = project;
	}
	
	public long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	
	
}
