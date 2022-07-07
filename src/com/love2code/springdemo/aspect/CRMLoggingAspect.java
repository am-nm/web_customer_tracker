package com.love2code.springdemo.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CRMLoggingAspect {

	// set up logger
	private Logger myLogger = Logger.getLogger(getClass().getName());
		
	// set up point cut declaration for controller
	@Pointcut("execution(* com.love2code.springdemo.controller.*.*(..))")
	private void forControllerPackage() {}
	
	// set up point cut declaration for service
	@Pointcut("execution(* com.love2code.springdemo.service.*.*(..))")
	public void forServicePackage() {}
	
	// set up point cut declaration for dao
	@Pointcut("execution(* com.love2code.springdemo.dao.*.*(..))")
	public void forDaoPackage() {}
	
	@Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
	private void forAppFlow() {}
	
	// add @Before advice
	@Before("forAppFlow()")
	public void before(JoinPoint theJoinPoint) {
		
		// display the method we are calling
		String theMethod = theJoinPoint.getSignature().toShortString();
		myLogger.info("====> in @Before calling method: " + theMethod);
		
		// display the arguments to the method
		
		// get the arguments
		Object[] args = theJoinPoint.getArgs();
		
		// loop through and display args
		for(Object tempArgs: args) {
			myLogger.info("======> arguments: " + tempArgs);
		}
		
	}
	
	// add @AfterReturning advice
	@AfterReturning(pointcut="forAppFlow()", returning="result")
	public void afterReturning(JoinPoint theJoinPoint, Object result) {
		
		// display method we are returning from
		String theMethod = theJoinPoint.getSignature().toShortString();
		myLogger.info("========> @AfterReturning from method: " + theMethod);
		
		
		// dsiplay the data returned
		myLogger.info("======> result: " + result);
	}
	
	
	
	
	
	
	
}
