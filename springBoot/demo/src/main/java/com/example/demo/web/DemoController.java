package com.example.demo.web;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/greeting")
public class DemoController 
{
	@RequestMapping("/hello")
	public String sayHello()
	{
		return "<h1>hello aaa bbb</h1>";
	}
	
	@RequestMapping("/proper")
	public String sayProperHello()
	{
		return "<h1>hello there, proper! How are you?</h1>";
	}
	
	@RequestMapping("/user_entry")
	public String userForm()
	{
		return "<form action=\"/greeting/user_greeting\" method=\"post\">\r\n" + 
		"  <label for=\"fname\">First name:</label><br>\r\n" + 
		"  <input type=\"text\" id=\"fname\" name=\"fname\" ><br>\r\n" + 
		"  <label for=\"lname\">Last name:</label><br>\r\n" + 
		"  <input type=\"text\" id=\"lname\" name=\"lname\" ><br><br>\r\n" + 
		"  <input type=\"submit\" value=\"Submit\">\r\n" + 
		"</form>";
	}
	
	@RequestMapping(value = "/user_greeting", method = RequestMethod.POST)
	public String printUserGreeting(@RequestParam String fname, @RequestParam String lname)
	{
		// fname과 lname은 request parameter이므로 위와 같은 annotation을 사용하여 지정해준다. 
		// 밑의 함수는 다른다. Url을 통해서 id 값이 전달될 것이다.
		return "Hello " + fname + " , " + lname; 
	}
	
	@RequestMapping(value = "/orders/{id}", method = RequestMethod.GET)
	public String getOrder(@PathVariable String id)
	{
		// PathVariable은 url을 통해서 값이 전달될 경우 사용하는 annotation이다. 
		return "Order ID : " + id;
	}
	
	
}
