package com.springProj.pma.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.springProj.pma.dto.EmployeeProject;
import com.springProj.pma.dto.EmployeeWorkChartData;
import com.springProj.pma.entity.Employee;

// PagingAndSortingRepository는 CrudRepository를 extends한다
// PagingAndSortingRepository가 Crud보다 약간의 method들이 더 있다


// @RepositoryRestResource는 우리가 rest api를 이용하기 위해서 사용하는 annotation
// application-dev.properties에 <artifactId>spring-boot-starter-test</artifactId> 가 있어
// 이건 spring data rest를 spring -> edit starters해서 pom.xml 파일에 추가를 한 것
// 현재 EmployeeController의 기본 url mapping이 "employees"이므로, rest api를 사용할 수가 없다. 이 mapping url을 바꾸지 않는 이상
// 그러므로, EmployeeController mapping url과 Rest API url을 따로 구분하기 위하여 
// @RepositoryRestResource 가 필요한 것 
// spring data를 rest api로 편하게 이용할 수 있는 강력한 기능
// 이 기능이 있으면, postman에서 하던 get post put delete 등을 다 할 수 있어

@RepositoryRestResource(collectionResourceRel="apiemployees", path="")
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long> 
{
	
//	@Override
//	public List<Employee> findAll();
	
	@Query(nativeQuery=true, value="select e.first_name as FirstName, e.last_name as LastName, count(pe.employee_id) as ProjectCount " + 
			"from employee e left join project_employee pe " + 
			"ON pe.employee_id = e.employee_id " + 
			"group by e.first_name, e.last_name " + 
			"order by 3 desc;")
	public List<EmployeeProject> employeeProjects();
	
	
	
	
	
	
	// @Query annotation을 주석처리해도 findByEmail() method가 정상으로 작동하는 이유는 
	// spring framework는 똑똑해서 이름의 rule만 지키면 알아서 query를 실행한다
	// spring의 JPA(Java Persistence API)를 뜻한다
	
	// Rules
	// findByNameAndEmail 의 형태로 작성. By 뒤부터 파싱
	// findByAgeOrderByNameDesc 처럼 order by 가능
	// findBy 말고 countBy, deleteBy도 있음
	// findDistinctBy 로 distinct 가능
	// 
	
	// @Query(nativeQuery=true, value="select * from employee where email = ?1")
	public Employee findByEmail(String value);
	// findEmployeeByEmail
	// 이름 위에 것도 가능
	// spring framework는 똑똑해서 우리가 이미 PagingAndSortingRepository<Employee, Long>로 extends를 하므로
	// employee를 찾는 것을 알고 있다
	
	
	@Query(nativeQuery=true, value="select * from employee where employee_id = ?1")
	public Employee findByEmployeeId(long id);
	// findByEmployeeId
	// 이름 위에 것도 가능
	// spring framework는 똑똑해서 우리가 이미 PagingAndSortingRepository<Employee, Long>로 extends를 하므로
	// employee를 찾는 것을 알고 있다
	
	
	
	
	
	@Query(nativeQuery = true, value = "select email as Email, start_date as startDate, end_date as endDate from employee")
	public List<EmployeeWorkChartData> getEmployeeTimeline();
	
	
	
	
}


