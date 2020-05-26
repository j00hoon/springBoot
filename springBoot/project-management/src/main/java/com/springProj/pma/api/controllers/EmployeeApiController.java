package com.springProj.pma.api.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.springProj.pma.dao.EmployeeRepository;
import com.springProj.pma.entity.Employee;

@RestController
@RequestMapping("/app-api/employees")
public class EmployeeApiController 
{
	// employee table에 접근하기 위해 사용 
	@Autowired
	EmployeeRepository empRepo;
	
	
	
	
	// @GetMapping에 아무런 url이 없으므로
	// 이 controller class 자체의 @RequestMapping에 대한 처리를 하는 method
	
	// 원래는 EmployeeRepository에서 findAll()을 
	// override 했었는데, 왜냐하면 List<> 형태로 return하기 위하여
	// 하지만, override하지 않고 그냥 Iterable<> type으로 return해도 된다
	@GetMapping
	public Iterable<Employee> getEmployees()
	{
		return empRepo.findAll();
	}
	
	
	
	@GetMapping("/{id}")
	public Employee getEmployeeById(@PathVariable("id") long id)
	{
		return empRepo.findByEmployeeId(id);
	}
	
	
	
	// consumes은 이 method가 기대하는 data type을 뜻한다
	
	// @ResponseStatus는 보통 request가 성공하면 200을 return하지만, 우리는 임의로 annotation을 사용해서 바꿨다. 201이 나올 것이다

	// @RequestBody type으로는 json이 필요하다. 왜냐하면
	// @PostMapping(consume = "~~")에서 이미 받아들이는 type으로 json을 지정했기 때문에
	// 우리가 만든 Employee type이 Json이다
	
	// @Valid는 Employee entity의 validation을 check하는 annotation
	// DB에서 하는 것이 아니라, controller level에서 validation을 check한다
	
	@PostMapping(consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Employee create(@RequestBody @Valid Employee employee)
	{
		return empRepo.save(employee);
	}
	
	
	
	
	
	
	// @PutMapping에서는 consumes을 이용하여 json type인지 알려주고 있지 않다
	// 왜냐하면 이미 우리가 @RequestBody에서 Employee를 사용하므로, 
	// springFramework는 @RequestBody annotation을 보고 json임을 안다. 똑똑
	// @PostMapping에서 consumes을 사용한 이유는 공부의 목적~
	
	// 둘 중의 하나 @PutMapping을 사용하면 된다
	//@PutMapping(consumes = "application/json")
	@PutMapping()
	@ResponseStatus(HttpStatus.OK)
	public Employee update(@RequestBody @Valid Employee employee)
	{
		return empRepo.save(employee);
	}
	
	
	
	
	// @PutMapping을 위와 같은 method로 사용을 하면
	// 현재 우리의 postgresql db는 cascade rule이 있으므로, 
	// 관련된 data들이 삭제되거나 원하지 않는 방향으로 update된다 
	// id는 그대로 놔두고, 다른 data들을 바꾸고 싶지만 현재 db의 구조는 그렇게 되지 않는다
	// id를 놔두고 다른 data들만 바꾸고 싶은 경우에는 밑의 method를 이용한다
	// @PatchMapping annotation을 이용한다
	
	@PatchMapping(path = "/{id}", consumes = "application/json")
	public Employee partialupdate(@PathVariable("id") long id, @RequestBody @Valid Employee patchEmp)
	{
		Employee emp = empRepo.findByEmployeeId(id);
		
		// email에 실제로 update가 있었는지 확인하는 if
		if(patchEmp.getEmail() != null)
		{
			emp.setEmail(patchEmp.getEmail());
		}// if
		if(patchEmp.getFirst_name() != null)
		{
			emp.setFirst_name(patchEmp.getFirst_name());
		}// if
		if(patchEmp.getLast_name() != null)
		{
			emp.setLast_name(patchEmp.getLast_name());
		}// if
		
		return empRepo.save(emp);
		
	}
	
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") long id)
	{
		// try-catch로 감싸야 계속해서 같은 id로 delete request를 보내도 error 405가 뜨지 않는다
		try
		{
			empRepo.deleteById(id);
		}// try
		catch(EmptyResultDataAccessException e)
		{
			
		}// catch
				
	}
	
	
	
	
	// @GetMapping에서 "page"는 page를 뜻하고, 
	// "size"는 해당하는 "page"에서 display할 data의 개수?라고 보면 된다
	// "page"는 0부터 시작
	
	// @RequestParam을 사용하는 방법은 postman get에서 
	// http://localhost:8080/app-api/employees/?page=0&size=6
	// 위와 같이 url을 get method로 send하면 된다 
	// page 0, 그리고 data size는 6개씩 잘라서 보여줘! 라는 소리
	
	@GetMapping(params= {"page", "size"})
	@ResponseStatus(HttpStatus.OK)
	public Iterable<Employee> findPaginatedEmployees(@RequestParam("page") int page, @RequestParam("size") int size)
	{
		Pageable pageAndSize = PageRequest.of(page, size);
		
		
		// EmployeeRepository에서 PagingAndSortingRepository를 extends한다 
		// PagingAndSortingRepository에서 findAll(Pageable)을 override해서 사용하므로, 
		// pageAndSize 변수 그대로 사용할 수 있는 것
		
		return empRepo.findAll(pageAndSize);
	}
	
	
	
	
}





