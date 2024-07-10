package com.example.springbootstarterconcurrency.aspect;

import com.example.springbootstarterconcurrency.annotation.Lockable;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Aspect
public class LockAspect {

    private final Map<String, Lock> locks = new ConcurrentHashMap<>();

    @Pointcut("@annotation(lockable)")
    public void lockPointcut(Lockable lockable) {
    }

    @Around("lockPointcut(lockable)")
    public Object lockAdvice(ProceedingJoinPoint joinPoint, Lockable lockable) throws Throwable {
        var lock = locks.computeIfAbsent(lockable.resource(), k -> new ReentrantLock());

        try {
            lock.lock();
            return joinPoint.proceed();
        } finally {
            lock.unlock();
        }
    }

}
