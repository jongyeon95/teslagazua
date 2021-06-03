package com.jongyeon.teslagazua.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class LoggerAspect {

    @Around("@annotation(CronLogging)")
    public Object  logCron(ProceedingJoinPoint pjp) throws Throwable{
        log.info("cron "+pjp.getSignature().getName());
        return pjp.proceed();
    }

    @Around("@annotation(ControllerLogging)")
    public Object  logController(ProceedingJoinPoint pjp) throws Throwable{
        log.info("enter "+pjp.getSignature().getName()+" controller");
        return pjp.proceed();
    }
 }
