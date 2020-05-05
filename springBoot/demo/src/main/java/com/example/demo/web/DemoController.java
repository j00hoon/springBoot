package com.example.demo.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/greeting")
public class DemoController 
{
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String sayHello()
	{
		return "<h1>hello aaa bbb</h1>";
	}
}
