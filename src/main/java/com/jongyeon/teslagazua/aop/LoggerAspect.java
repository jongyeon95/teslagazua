package com.jongyeon.teslagazua.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
@Slf4j
public class LoggerAspect {

    @Around("@annotation(CronLogging)")
    public Object  logCron(ProceedingJoinPoint pjp) throws Throwable{
        log.info("exec "+pjp.getSignature().getName());
        Object retVal = pjp.proceed();
        return retVal;
    }
 }
