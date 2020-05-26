package com.springProj.pma.entity;

import java.util.Date;
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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springProj.pma.validation.UniqueValue;

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
	
	// @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="employee_seq")
	// postgreSql을 사용하면서, PK가 nextval 하나씩 증가하는 sequence로 바뀌어서 GenerationType도 SEQUENCE로 바꿈. 
	// 바꿔야지 hibernate가 더 빨리 인식함
	// generator는 이 PK를 생성하는 sequence의 이름을 적어주면 된다
	@Id
	@SequenceGenerator(name="employee_seq_gen", sequenceName = "employee_seq", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="employee_seq_gen")
	private long employee_id;
	
	
	
	
	
	
	
	// @NotNull은 null값이 들어올 수 없다는 constraint
	// @Size(min = 2, max = 50) 은 min은 2 characters, max는 50 characters
	// @Email은 email type? email format? constraint
	// @Column(unique = true, nullable = false) 은 unique한 email이어야하고, NotNull의 또 다른 표현 방식
	
	// @NotNull, @Size, @Email은 javax.validation 즉, java code의 controller level에서 validation을 조사한다 
	// 그러므로, code를 compile 할 때 이미 validation check를 해서 확인
	// 하지만, @Column은 javax.persistence 즉, DB level에서 validation을 확인하므로, DB에서 작업을 할 때 validation 조사를 한다
	// Error가 나는 시점이 다른 것
	
	
	//@NotNull
	@NotBlank
	@Size(min = 2, max = 50)
	private String first_name;
	
	//@NotNull
	@NotBlank(message="Musge give a last name")
	@Size(min = 2, max = 50, message = "last name size messages")
	private String last_name;
	
	//@NotNull
	@NotBlank
	//@Column(unique = true, nullable = false)  // custom validator를 적용 ==> @UniqueValue
	@Email(message="Must be a valid email address")
	@UniqueValue	
	private String email;
	
	
	@NotBlank
	private Date start_date;
	
	@NotBlank
	private Date end_date;
	
	
	
	
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
//	@JoinColumn(name="projectId")
//	private Project project;
	
	
	
	
	
	// @ManyToMany이므로 Project변수를 List로 만든다
	
	// @JsonIgnore가 하는 일은 List<Project> project변수에 대해서 serialized 되는 것을 ignore한다.
	// 현재, EmployeeApiController의 getEmployees() 에서 findAll()을 return하는데 type이 Iterable이다. 
	// Employee와 Project entity들을 보면, 둘 다 List<>의 형태로 서로의 List Object를 갖고 있고, 
	// 이것은 서로를 serialization한다는 뜻. 
	// 거의 infinite loop에 빠진다고 보면 되고, 실제로 error도 발생
	// 그러므로, 서로 Iterable type으로 return을 하기 위해서는 서로 serialized 되는 것을 막기 위하여
	// @IgnoreJson annotation이 필요하다. 
	// Project, Employee entity 모두
	
	@ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST}, 
			fetch = FetchType.LAZY)
	@JoinTable(name="Project_Employee", 
	joinColumns=@JoinColumn(name="employee_id"), 
	inverseJoinColumns=@JoinColumn(name="project_id"))
	@JsonIgnore
	private List<Project> project;
	
	public Employee() {}
	
	public Employee(String first_name, String last_name, String email, Date start_date, Date end_date) 
	{
		super();
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.start_date = start_date;
		this.end_date = end_date;
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
	
	public long getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(long employee_id) {
		this.employee_id = employee_id;
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

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	
	
	
	
	
	
	
}
