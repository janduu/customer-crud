package com.example.spring.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Aspect
@Component
public class AppLoggingAspect {
    private static final Logger LOGGER = Logger.getLogger(AppLoggingAspect.class.getName());

    @Before("com.example.spring.aop.pointcut.PointCutExpression.appPointCut()")
    public void logAppMethodsBeforeMethodExecutes(JoinPoint joinPoint) {
        String methodSignature = joinPoint.getSignature().toShortString();
        String params = Arrays.stream(joinPoint.getArgs()).toList().toString();

        if (params.isEmpty() || params.equals("[]")) {
            params = "No params";
        }

        LOGGER.log(Level.INFO,"{0} is called! with params: {1}",
                new Object[] {methodSignature, params});
    }

    @AfterReturning(
            pointcut = "com.example.spring.aop.pointcut.PointCutExpression.appPointCut()",
            returning = "returnedValue"
    )
    public void logAppMethodsAfterReturning(JoinPoint joinPoint, Object returnedValue) {
        String returnToLog;
        if (isNotNullCollectionOrMapOrArray(returnedValue)) {
            returnToLog = "Collection, Map or Array";
        } else {
            returnToLog = returnedValue + "";
        }
        String methodSignature = joinPoint.getSignature().toShortString();
        LOGGER.log(Level.INFO, "{0} returned value: {1}",
                new Object[] {methodSignature, returnToLog});
    }

    private static boolean isNotNullCollectionOrMapOrArray(Object obj) {
        if (obj == null) {
            return false;
        }
        return obj instanceof Collection || obj instanceof Map || obj.getClass().isArray();
    }
}
