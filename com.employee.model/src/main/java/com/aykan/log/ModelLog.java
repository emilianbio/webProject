package com.aykan.log;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ModelLog {

	private final Logger LOGGER = LogManager.getLogger(ModelLog.class);
	
	@Around("daoMethods()")
	public Object daoAdvice(ProceedingJoinPoint joinPoint){
	
		Object object = null;
		try{
			
			object = joinPoint.proceed();
				
		}catch (Throwable e) {
			LOGGER.warn(joinPoint + " : " + e);
		}
		return object;
	}
	
	@Pointcut("within(com.aykan.dao.*.impl.*)")
	public void daoMethods(){}
	
	@Around("serviceMethods()")
	public Object serviceAdvice(ProceedingJoinPoint joinPoint){
		
		Object object = null;
		try{
		
			object = joinPoint.proceed();

		}catch (Throwable e) {
			LOGGER.warn(joinPoint + " : " + e);
		}
		return object;
	}
	
	@Pointcut("within(com.aykan.service.*.impl.*)")
	public void serviceMethods(){}
	

}
