package com.example.springbootstarterconcurrency.aspect;

import com.example.springbootstarterconcurrency.annotation.UseSemaphore;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;

@Aspect
public class UseSemaphoreAspect {

    private static final Logger LOG = LoggerFactory.getLogger(UseSemaphoreAspect.class);

    private final Map<String, Semaphore> semaphores = new ConcurrentHashMap<>();

    @Pointcut("@annotation(useSemaphore)")
    public void useSemaphorePointcut(UseSemaphore useSemaphore) {
    }

    @Around("useSemaphorePointcut(useSemaphore)")
    public Object useSemaphoreAdvice(ProceedingJoinPoint joinPoint, UseSemaphore useSemaphore) throws Throwable {
        var semaphore = semaphores.computeIfAbsent(
                useSemaphore.target(), k -> new Semaphore(useSemaphore.permits()));

        try {
            semaphore.acquire();
            LOG.info("Семафор захвачен потоком: {}.", Thread.currentThread().getName());
            return joinPoint.proceed();
        } finally {
            semaphore.release();
            LOG.info("Семафор отпущен потоком: {}.", Thread.currentThread().getName());
        }

    }

}
