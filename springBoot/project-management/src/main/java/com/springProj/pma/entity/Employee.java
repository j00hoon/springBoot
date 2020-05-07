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
	// @GeneratedValue(strategy = GenerationType.AUTO)에서 AUTO는 hibernate가 id의 값을 
	// 자동으로 increase 해주길 원할 때 쓴다. 예를 들어, 
	// data.sql 파일이 없다고 가정한다면, 우리가 data를 DB에 넣어줘야 하는데 
	// DB에 넣어줄 때, hibernate가 id를 자동으로 increase 해준다는 소리.
	// 하지만, 우리가 data.sql 파일을 쓴다면 처음부터 insert를 이용하여 데이터를 DB에 넣으므로 
	// hibernate는 데이터가 들어갔는지 알지 못하므로, 그대로 AUTO로 쓰게된다면 id를 1부터 쓰기 때문에
	// id 중복이 일어난다. employeeId가 PK라면, 중복으로 error가 발생.
	//@GeneratedValue(strategy = GenerationType.AUTO)
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long employeeId;
	
	private String first_name;
	private String last_name;
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
	
	public Employee(String first_name, String last_name, String email) 
	{
		super();
		this.first_name = first_name;
		this.last_name = last_name;
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

	

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	
	
}
