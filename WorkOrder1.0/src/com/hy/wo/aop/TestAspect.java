package com.hy.wo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TestAspect {

	@Before("execution(* com.hy.wo.service.impl.WorkOrderServiceImpl.*(..))")
	public void doBefore(JoinPoint jp)
	{
		System.out.println("方法处理前: "  + jp.getTarget().getClass().getName() + "."+ jp.getSignature().getName());  

	}
	@After("execution(* com.hy.wo.service.impl.WorkOrderServiceImpl.*(..))")
	public void doAfter(JoinPoint jp){
		System.out.println("方法处理后: "  + jp.getTarget().getClass().getName() + "."+ jp.getSignature().getName());
	}
}
