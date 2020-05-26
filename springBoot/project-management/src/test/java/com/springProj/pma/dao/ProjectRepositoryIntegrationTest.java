package com.springProj.pma.dao;



import static org.junit.Assert.assertEquals;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;


import com.springProj.pma.entity.Project;


// @ContextConfiguration(classes=ProjectManagementApplication.class) -> 이것을 하는 이유는 
// IntegrationTest를 할 때, original application의 starting poing를 알려주면 더욱 빠르고 쉽기 때문
// Starting point를 알려주면, 원래 application의 동작처럼 @Autowired 혹은 생성되는 bean들도 자동으로 모두 
// 읽어서 test에 사용하기 때문
// 이렇게 Starting Point를 지정해주는 불편함을 덜기 위해서, @SpringBootTest annotation을 사용해야 한다
// @ContextConfiguration(classes=ProjectManagementApplication.class)


// @DataJpaTest는 우리가 test를 위한 DB가 따로 있는 경우에 사용해야할 annotation이다
// 지금같은 경우는 우리가 h2 DB를 test DB로 사용하기 때문에
// @DataJpaTest를 사용한다
// @DataJpaTest

// src/test/java path밑에 있는 package들의 이름도 
// 실제 application packages처럼 이름들을 맞춰줘야 한다

// -------------> @SpringBootTest annotation을 사용하면, @DataJpaTest annotation도 필요없다





// @RunWith(SpringRunner.class) --> 이것은 Junit testing을 위해 필요한 annotation이다



// @SqlGroup 과 @Sql은 우리가 사용하고 싶은 sql파일이 있을 때, 사용하는 annotation
// 만약 sql파일이 하나라면 @Sql을 바로 사용할 수 있지만, 
// sql파일이 여러개면 @SqlGroup을 사용해야 한다
// executionPhase = ExecutionPhase.BEFORE_TEST_METHOD --> sql을 test method가 실행되기 전에 실행하라는 의미
// executionPhase = ExecutionPhase.AFTER_TEST_METHOD --> sql을 test method가 끝난 후에 실행하라는 의미
// 사실 이 코드에서는 ExecutionPhase.AFTER_TEST_METHOD 이 부분은 필요없지만, 공부 차원에서

// @SqlGroup annotation을 제거하고 test를 돌리고 싶다면 ??????? --> assertEquals(5, proRepo.findAll().size()); 이 코드에서
// 5를 1로 바꾸면 된다. 왜냐하면??????
// data.sql파일이 실행이 되지 않기 때문에, 미리 insert 되는 data가 없기 때문




@SpringBootTest
@RunWith(SpringRunner.class)
@SqlGroup({@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts= {"classpath:schema.sql", "classpath:data.sql"}), 
			@Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts= "classpath:drop.sql")})
public class ProjectRepositoryIntegrationTest 
{
	// @Autowired annotation을 이용하면,
	// @ContextConfiguration(classes=ProjectManagementApplication.class) --> 이 annotation 덕분에
	// 실제 application에서 만드는 bean이나 context 등이 load 될 것이다
	@Autowired
	ProjectRepository proRepo;
	
	@Test
	public void ifNewProjectSaved_thenSuccess()
	{
		Project newProject = new Project("New Test Project", "COMPLETE", "Test Description");
		proRepo.save(newProject);
		
		assertEquals(5, proRepo.count());
		
	}
	
}






