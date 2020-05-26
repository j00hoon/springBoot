package com.springProj.pma.dao;

import org.springframework.data.repository.CrudRepository;

import com.springProj.pma.entity.UserAccount;


public interface UserRepository extends CrudRepository<UserAccount, Long> 
{
	
	
	
}
