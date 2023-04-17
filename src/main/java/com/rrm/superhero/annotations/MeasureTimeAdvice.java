package com.rrm.superhero.annotations;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class MeasureTimeAdvice {

    @Around("@annotation(com.rrm.superhero.annotations.MeasureTime)")
    public Object measureTime(ProceedingJoinPoint point) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object object = point.proceed();
        stopWatch.stop();
        log.info("Time take by " + point.getSignature().getName() + "() method is " 
            + stopWatch.getTotalTimeMillis() + " ms");
        return object;
    }
}