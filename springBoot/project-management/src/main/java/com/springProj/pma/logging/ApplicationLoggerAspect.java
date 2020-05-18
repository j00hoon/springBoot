package com.springProj.pma.logging;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


// @Aspect, @Componet로 이 클래스가 AOP가 바라보는 관점을 정의하고, bean으로 등록하는 것을 정의

@Aspect
@Component
public class ApplicationLoggerAspect 
{
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	
	
	
	// @Pointcut은 where? 만 기억하면 된다 
	
	// 어디에 내가 cross-cut을 하고 싶은지
	// ..*의 의미는 com.springProj.pma.controllers 안의 class 및 method들은 모두 해당한다는 뜻
	// com.springProj.pma.dao 안의 class와 method도 해당이 된다. 
	// Multiple pointcuts을 지정할 수 있다
	
	// 밑과 같이 multiple로 pointcuts을 지정할 수도 있고, 직접 method에서 Joinpoint를 이용해서 
	// 언제 실행될 지 정하는 것도 가능하다
	
	//@Pointcut("within(com.springProj.pma.controllers..*)" + "|| within(com.springProj.pma.dao..*)")
	@Pointcut("within(com.springProj.pma.controllers..*)")
	public void definePackagePointCuts()
	{
		// empty method just to name the location specified in the pointcut
	}
	
	
	
	
	// definePackagePointCuts() method 의 location은 현재 com.springProj.pma.controllers 안의 모든 것으로 지정되어 있다
	// 그러므로, log() method는 com.springProj.pma.controllers 안의 모든 것이 
	// 실행된 후에 @After, log() method가 실행된다,, @Before는 실행되기 전에
	
	// Joinpoint란, advice를 적용하는 지점을 의미한다. method call 혹은 variable 값 변경 등이 해당된다 
	// It is a point during which the execution is taking place
	
//	@Before("definePackagePointCuts()")
//	public void logBefore(JoinPoint jp)
//	{
//		log.debug("\n\n\n--------------------------------------- before log method ------------------------\n {}.{} () with arguments[s] = {}\n\n\n", 
//				jp.getSignature().getDeclaringTypeName(), 
//				jp.getSignature().getName(), 
//				Arrays.toString(jp.getArgs()));
//		log.debug("\n\n\n--------------------------------------------- THE END ---------------------------------\n\n\n");
//		
//	}
	
	
//	@After("definePackagePointCuts()")
//	public void logAfter(JoinPoint jp)
//	{
//		log.debug("\n\n\n--------------------------------------- after log method ------------------------\n {}.{} () with arguments[s] = {}\n\n\n", 
//				jp.getSignature().getDeclaringTypeName(), 
//				jp.getSignature().getName(), 
//				Arrays.toString(jp.getArgs()));
//		log.debug("\n\n\n--------------------------------------------- THE END ---------------------------------\n\n\n");
//	}
	
	
	
	
	
	// @Around 하나면 @Before와 @After 둘 다 동시에 사용할 수 있다
	
	@Around("definePackagePointCuts()")
	public Object logAround(ProceedingJoinPoint jp)
	{
		log.debug("\n\n\n--------------------------------------- before log method ------------------------\n {}.{} () with arguments[s] = {}\n\n\n", 
				jp.getSignature().getDeclaringTypeName(), 
				jp.getSignature().getName(), 
				Arrays.toString(jp.getArgs()));
		log.debug("\n\n\n--------------------------------------------- THE END ---------------------------------\n\n\n");
		
		
		
		
		Object obj = null;
		
		try
		{
			obj = jp.proceed();
		}// try 
		catch (Throwable e) 
		{
			e.printStackTrace();
		}// catch
		
		
		
		
		log.debug("\n\n\n--------------------------------------- after log method ------------------------\n {}.{} () with arguments[s] = {}\n\n\n", 
				jp.getSignature().getDeclaringTypeName(), 
				jp.getSignature().getName(), 
				Arrays.toString(jp.getArgs()));
		log.debug("\n\n\n--------------------------------------------- THE END ---------------------------------\n\n\n");
		
		return obj;
	}
	
}
