package com.springProj.pma.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springProj.pma.dao.UserRepository;
import com.springProj.pma.entity.UserAccount;


@Controller
@RequestMapping("/register")
public class SecurityController 
{
	@Autowired
	BCryptPasswordEncoder bCryptEncoder;
	
//	@Autowired
//	UserAccountService userService;
	
	@Autowired
	UserRepository userRepo;
	
	
	// "/register" 에서 user를 등록하고, "submit" button을 누르면 밑에 saveUser()로 user data가 post 되겠지
	@GetMapping
	public String register(Model model)
	{
		UserAccount userAccount = new UserAccount();
		model.addAttribute("userAccount", userAccount);
		
		return "security/register";
	}
	
	
	@PostMapping("/save")
	public String saveUser(Model model, UserAccount userAccount)
	{
		// user data 중에서 password를 암호화하는 작업
		userAccount.setPassword(bCryptEncoder.encode(userAccount.getPassword()));
		
		userRepo.save(userAccount);
		
		// 이렇게 "redirect"가 있으면 무조건 CSRF Token을 위한 Security 처리 작업이 필요하다 !!!
		return "redirect:/";
	}
}
