package com.springProj.pma.controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


// error만 관리하는 controller
// ErrorController에는 method가 하나 있다 
// getErrorPath()인데, 이 method를 이용해서 "/error"를 관리한다 

@Controller
public class AppErrorController implements ErrorController 
{

	// 이 method에서 "/error"를 관리한다
	@GetMapping("/error")
	public String handleError(HttpServletRequest request)
	{
		// Object인 이유는 아직 어떤 type으로 return이 올 지 모르기 때문에
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		
		if(status != null)
		{
			Integer statusCode = Integer.valueOf(status.toString());
			
			// error 404 
			if(statusCode == HttpStatus.NOT_FOUND.value())
			{
				return "errorpages/error-404";
			}// if
			// error 500 
			else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value())
			{
				return "errorpages/error-500";
			}// else if
			// error 403
			else if(statusCode == HttpStatus.FORBIDDEN.value())
			{
				return "errorpages/error-403";
			}// else if
		}// if
		
		return "errorpages/error";
	}
	
	@Override
	public String getErrorPath() 
	{
		return "/error";
	}
	
	
	

}
