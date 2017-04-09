package com.aykan.security.aop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingSecurity {
	
	private final Logger LOGGER = LogManager.getLogger();
	
	@Around("@annotation(com.aykan.security.aop.Loggable)")
	public Object securityMethods(ProceedingJoinPoint joinPoint){
		Object object = null;
		try {
			object = joinPoint.proceed();
		
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			LOGGER.warn("ERROR : " + joinPoint + " - " + e.getMessage());
		}
		return object;
	}
	
}
