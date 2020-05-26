package com.springProj.pma.services;

// @Service annotation이 있으면, spring framework에서 annotation이 있는 class를 object로 만들어서 context에 추가를 한다
// @Component annotation도 같은 역할을 한다, @Repository annotation도 같은 역할
// @Service는 class를 service로써 사용하고 싶은 경우
// @Repository는 class를 data로써 사용하고 싶은 경우
// @Component는 class를 그냥 어떤 형태로 쓸 지 확신이 없을 경우 -> @Service와 @Repository 그냥 구분하기 어려울 경우에 쓰는 것 같음

// 만약에 어떤 class가 main class의 package 밑에 없는 경우 
// ex -> package의 이름이 com.springProj.pma.~~ 가 아니라 
// com.springProj.~~ 처럼 다른 경우에는 평범한 방법으로는 spring이 scan을 하지 못한다
// main class의 @SpringBootApplication()에 scanBasePackages를 줄 수 있다 
// package 이름이 com.springProj.utils 그리고 com.springProj.test 이라면
// @SpringBootApplication(scanBasePackages={"com.springProj.pma", "com.springProj.utils", "com.springProj.test"})
// 이렇게 추가로 scan을 하고 싶은 class들이 속해 있는 package들을 추가시켜야 한다.
// 이 때 !!!!!!!!!!!!!!!!!!!!
// "com.springProj.pma" --> default package도 반드시 추가를 해줘야 application이 정상적으로 동작된다 
// 그냥 웬만하면 모두 default package 이름을 따르는 것이 좋은 것 같다

//@Service
public class DataCleansingService 
{
	public DataCleansingService() 
	{
		super();
	}
}
