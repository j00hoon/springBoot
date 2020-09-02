package com.example.demo.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Product;

@RestController
@RequestMapping("/user")
public class userController 
{
	@RequestMapping("/{userId}")
	public String displayUser(@PathVariable int userId)
	{
		return "User ID : " + userId;
	}
	
	@RequestMapping("/{id}/invocies")
	public String displayUserInvocies(@PathVariable("id") int userId, @RequestParam(value="date", required=false) Date dateOrNull)
	{
		return "Invocie for User : " + userId + " on the date : " + dateOrNull;
	}
	
	@RequestMapping("/{id}/items")
	public List<String> displayStringJson()
	{
		return Arrays.asList("Shoes", "Laptop", "Car");
	}
	
	@RequestMapping("/{id}/products_as_json")
	public List<Product> displayProductsJson()
	{
		return Arrays.asList(
				new Product(1, "shoes", 42.33), 
				new Product(2, "home", 990505050.0), 
				new Product(3, "Car", 990090900.0));
	}
	
}
