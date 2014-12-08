package com.dariksoft.kalatag.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@Aspect
public class MyLogger {

    private Logger log = LoggerFactory.getLogger(MyLogger.class);

    @After("execution(* com.dariksoft.kalatag.service.person.PersonServiceImp.create(..))")
    public void log(JoinPoint point) {
        log.debug("------------>"+point.getSignature().getName() + " called...");
    }
}
