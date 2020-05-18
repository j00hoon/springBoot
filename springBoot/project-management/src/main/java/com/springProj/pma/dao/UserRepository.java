package com.springProj.pma.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.springProj.pma.dto.ProjectStage;
import com.springProj.pma.entity.Project;
import com.springProj.pma.entity.UserAccount;


public interface UserRepository extends CrudRepository<UserAccount, Long> 
{
	
	
	
}
