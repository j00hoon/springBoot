package com.example.demo.controller;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/greeting")
public class helloController 
{
	//@GetMapping(path="/hello")
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String helloSpringBoot()
	{
		return "hello111";
	}
	
	@RequestMapping(value = "/hello2")
	public String hello2SpringBoot()
	{
		return "hello222";
	}
	
	@RequestMapping(value = "/user_entry")
	public String userForm()
	{
		return "<form action=\"/greeting/user_greeting\" method=\"POST\">\r\n" + 
		"  <label for=\"fname\">First name:</label><br>\r\n" + 
		"  <input type=\"text\" name=\"fname\"><br>\r\n" + 
		"  <label for=\"lname\">Last name:</label><br>\r\n" + 
		"  <input type=\"text\" name=\"lastname\"><br><br>\r\n" + 
		"  <input type=\"submit\" value=\"Submit\">\r\n" + 
		"</form>";
	}
	
	@RequestMapping(value = "/user_greeting", method = RequestMethod.POST)
	public String printUserGreeting(@RequestParam String fname, @RequestParam String lastname)
	{
		return "First Name : " + fname + ", Last Name : " + lastname;
	}
	
	@RequestMapping(value = "/orders/{id}", method = RequestMethod.GET)
	public String getOrder(@PathVariable String id)
	{
		return "Order ID : " + id;
	}
	
	
}
