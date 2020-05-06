package com.example.demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.domain.User;

@Controller
public class HomeController 
{
	@RequestMapping("/")
	public String home(Model model)
	{
		model.addAttribute("formData", new User());
		return "index";
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String processFormData(User user, RedirectAttributes attr)
	{
		// This method for inserting data submitted in the form to the database
		
		// Put value user to the key 'userKey' and then use the key from result.html
		attr.addFlashAttribute("userKey", user);
		return "redirect:/display";
	}
	
	@RequestMapping("/display")
	public String displayFormData(User user)
	{
		return "result";
	}
	
	
}
