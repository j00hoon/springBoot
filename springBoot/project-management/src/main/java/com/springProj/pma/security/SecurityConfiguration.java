package com.springProj.pma.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter 
{
	// SpringBoot는 여기서 사용하는 dataSource가 
	// h2 dataSource라는 것을 자동으로 인식할 정도로 똑똑하다
	// application-dev.properties에서 DB를 postgreSql로 할지, h2 DB로 할지에 따라서 인식하는 DB가 달라진다
	// 그 만큼 spring boot이 똑똑하다는 소리
	@Autowired
	DataSource dataSource;
	
	
	@Autowired
	BCryptPasswordEncoder bCryptEncoder;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception 
	{
		// 이렇게 code에 보이도록 user, password를 설정하는 것은 매우 안 좋음.
		// 다른 코드로 바뀔 것이다
		// user를 또 지정할 수 있다
		
		// 처음에 따로 db와 연결하지 않은 상태에서 사용한 Authentication()
		//auth.inMemoryAuthentication()
		
		// withDefaultSchema()를 함께 실행하면,
		// DB에 기본적인 table을 만들어준다. 
		// user나 auth table 등을 만들어줘서 
		// 우리가 지금 이 코드에서 지정하는 user들의 rule들을 support해준다
		auth.jdbcAuthentication().dataSource(dataSource)
			//.withDefaultSchema()
			
			// withDefaultSchema()를 사용하지 않고, 내가 원하는 권한? authentication을 주고 싶다면?
			// 밑의 두 줄처럼 하면 된다. 
			// 나머지 withUser()나 password() 등등 다 지우고
			.usersByUsernameQuery("select username, password, enabled from user_accounts where username=?")
			.authoritiesByUsernameQuery("select username, role from user_accounts where username=?")
			.passwordEncoder(bCryptEncoder);
			//.passwordEncoder(getPasswordEncoder()); // 이 방법은 좋지 않아. password는 encrypted(암호화)가 된 상태로 와야 하므로
													// Therefore, WebConfig.java에서 관련 작업을 한다
													// 위와 같이, bCryptEncoder 변수를 사용. 이 변수는 이미 암호화가 된 상태로 옴
													// 이렇게되면, 밑의 getPasswordEncoder()도 필요가 없어지므로 주석처리
		
//		.withUser("myuser")
//			.password("password")
//			.roles("USER")
//		.and()
//		.withUser("j00hoon")
//			.password("password")
//			.roles("USER")
//		.and()
//		.withUser("managerUser")
//			.password("password3")
//			.roles("ADMIN");
		
	}
	
	
	// @Bean annotation이므로, application이 load되면 자동으로 register 될 것 이다.
//	@Bean
//	public PasswordEncoder getPasswordEncoder()
//	{
//		// configure method에서 처음에 user와 password를 code에 보이도록 설정했다
//		// 하지만 spring framework는 우리가 password를 설정해도 encoder를 하지 않았기 때문에? 인식?을 못한다고 해야하나?
//		// 아무튼 password가 code에서 바로 보여지므로 매우 안 좋은 방식.
//		// 하지만 test 등의 목적으로 사용하기 위해서 밑의 'return ~~' 부분을 통해 우리가 직접 지정해준 password를 
//		// encoding 시켜줘서 spring framework에게 전달한다. 추후에 바뀔 예정
//		return NoOpPasswordEncoder.getInstance();
//	}
	
	
	// 이 method에서 우리는 login한 user가 어떤 행동? 권한? 을 할 수 있는지 지정할 수 있다
	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		// antMatchers("/projects/new").hasRole("ADMIN")은 
		// role이 "ADMIN"인 user만 해당하는 endPoint("/projects/new")에 접근할 수 있다는 것
		
		// 제일 밑에 "/"와 "/**"는 모든 user가 "/"와 "/**" 접근이 가능하도록 설정. "/**"는 "/" 다음 어떤 end point든 다 해당
		
		// antMatchers(~~)들은 위에서부터 priority가 있다. 그러므로, 위에서부터 적용되는 priority가 쎈 것
		
		// hasRole()과 hasAuthority()의 차이는 간단. DB에 저장 혹은 update를 할 경우에 
		// hasRole("ADMIN")은 예를 들어서, 
		// update ~~ set role = 'ROLE_ADMIN' 처럼 앞에 'ROLE_'을 붙여줘야한다
		// hasAuthority()는 앞에 'ROLE_'이 필요없다
		http.authorizeRequests()
			//.antMatchers("/projects/new").hasRole("ADMIN")
			//.antMatchers("/projects/save").hasRole("ADMIN")
			//.antMatchers("/employees/new").hasRole("ADMIN")
			//.antMatchers("/employees/save").hasRole("ADMIN")
//			.antMatchers("/employees/new").hasAuthority("ADMIN")
//			.antMatchers("/employees/save").hasAuthority("ADMIN")
			//.antMatchers("/h2-console/**").permitAll() // h2 DB를 안 쓸 때엔 필요없어
			.antMatchers("/", "/**").permitAll() // 여러가지 pattern을 지정할 수 있다
			.and()
			.formLogin();
		
			// 이렇게하면, 우리가 만든 login page를 이용할 수 있다. default login page 말고. logout page도 가능
			//.formLogin().loginPage("/login-page") 
		
		http.csrf().disable();
		
	}
	
}
