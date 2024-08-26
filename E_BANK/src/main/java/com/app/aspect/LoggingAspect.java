package com.app.aspect;



import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.app.LoggerSingleton;

@Aspect
@Component
public class LoggingAspect {
	
	
	private final LoggerSingleton logger;

    public LoggingAspect(LoggerSingleton loggerSingleton) {
        this.logger = loggerSingleton;
    }
    
    @Before("execution(* com.app.service.CustomerServiceImpl.*(..))")
    public void logBeforeLogin(JoinPoint joinPoint ) {
    	logger.getLogger().info("Loggin in user "+ joinPoint.getArgs()[0]);
    }
    
    @After("execution(* com.app.service.CustomerServiceImpl.*(..))")
    public void logAfterLogin(JoinPoint joinPoint) {
    	logger.getLogger().info("User Logged in Successfully "+ joinPoint.getArgs()[0]);
    }

    @Before("execution(* com.app.service.PaymentServiceImpl.*(..))")
    public void logBeforTransaction(JoinPoint joinPoint ) {
    	logger.getLogger().info("Transaction Started for "+ joinPoint.getArgs()[0]);
    }
    
    @After("execution(* com.app.service.PaymentServiceImpl.*(..))")
    public void logAfterTransaction(JoinPoint joinPoint) {
    	logger.getLogger().info("Transaction Completed for "+ joinPoint.getArgs()[0]);
    }

}
