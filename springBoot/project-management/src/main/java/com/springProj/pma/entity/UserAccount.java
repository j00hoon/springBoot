package com.springProj.pma.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "user_accounts") // 실제 postgreSql db안의 어떤 table과 연결될 것인지 직접 name으로 지정
public class UserAccount 
{
	@Id
	@SequenceGenerator(name="user_accounts_seq_gen", sequenceName = "user_accounts_seq", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_accounts_seq_gen")
	@Column(name = "user_id")
	private long userId;
	
	@Column(name = "username") // 위의 @Table과 마찬가지로 실제 DB안의 어떤 column과 연결될 지 직접 name으로 지정
	private String userName;
	
	private String email;
	
	private String password;
	
	private boolean enabled = true;
	
	public UserAccount() {}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	
	
}
