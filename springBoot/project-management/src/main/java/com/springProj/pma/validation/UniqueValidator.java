package com.springProj.pma.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.springProj.pma.dao.EmployeeRepository;
import com.springProj.pma.entity.Employee;



// 이 custom validator는 Employee.java의 email field에서 사용할 것이므로
// UniqueValue와 String을 각각 실제로 사용할 annotation과 data type으로 사용해야 함



public class UniqueValidator implements ConstraintValidator<UniqueValue, String> 
{

	// 이 class에서 실제로 employee table에 접근하여 email이 존재하는지 확인하여야 하므로 EmployeeRepository가 필요
	@Autowired
	EmployeeRepository empRepo;
	
	
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context)
	{
		System.out.println("Entered validation method..............................................");
		
		// 현재의 code로 새로운 Json data를 employee table에 post하려고 하면
		// double validation이 일어난다 
		// 그러므로 NullPointerException이 일어난다
		// read 할 때와 insertion 할 때 일어난다
		// client check할 때와 insertion 두 번
		
		// 우리가 원하는 것은 read가 아닌, insertion 때 검사해서 email이 존재하지 않으면 
		// 새로운 Json data를 table에 넣는 것을 원한다
 
		// 두 번 이 isValid() method가 불리는 것을 막기 위해서는  application-dev.properties에 가서 밑에를 추가한다
		// spring.jpa.properties.javax.persistence.validation.mode=none
		
		Employee emp = empRepo.findByEmail(value);
		
		if(emp != null)
		{
			// email이 존재하면
			return false;
		}// if
		else
		{
			return true;
		}// else
		
	}
	
	
	
	
	
	
	
}
