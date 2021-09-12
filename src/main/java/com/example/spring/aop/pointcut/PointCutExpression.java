package com.example.spring.aop.pointcut;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PointCutExpression {
    @Pointcut("execution(* com.example.spring.controller.*.*(..))")
    public void controllerPointCut() { }

    @Pointcut("execution(* com.example.spring.dao.*.*(..))")
    public void daoPointCut() { }

    @Pointcut("execution(* com.example.spring.service.*.*(..))")
    public void servicePointCut() { }

    @Pointcut("controllerPointCut() || servicePointCut() || daoPointCut()")
    public void appPointCut() { }
}
