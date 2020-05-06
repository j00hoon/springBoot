package com.example.demo.web;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Product;

@RestController
@RequestMapping("/user")
public class UserController 
{
	@RequestMapping("/{id}")
	public String displayUser(@PathVariable int id)
	{
		return "User Found : " + id;
	}
	
	@RequestMapping("/{idVoices}/invoices")
	public String displayUserInvoices(@PathVariable(value = "idVoices") int userId, 
									@RequestParam(value = "date", required = false) Date dateOrNull)
	{
		return "Invoices found for user : " + userId + ", On the date : " + dateOrNull;
	}
	
	@RequestMapping("/{userId}/items")
	public List<String> displayStringJson()
	{
		return Arrays.asList("Shoes", "Laptop", "Xbox");
	}
	
	@RequestMapping("/{userId}/products_as_json")
	public List<Product> displayProductJson()
	{
		return Arrays.asList(new Product(1, "shoes", 42.99), new Product(2, "book", 52.55), new Product(3, "Laptop", 1845));
	}
	
}
