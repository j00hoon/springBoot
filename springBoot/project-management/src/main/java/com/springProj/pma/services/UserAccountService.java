package com.springProj.pma.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.springProj.pma.dao.UserRepository;
import com.springProj.pma.entity.UserAccount;



public class UserAccountService 
{
	@Autowired
	UserRepository secRepo;

	public UserAccount save(UserAccount userAccount) 
	{
		return secRepo.save(userAccount);
	}
	
}
