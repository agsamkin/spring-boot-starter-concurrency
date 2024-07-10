package com.example.springbootstarterconcurrency.aspect;

import com.example.springbootstarterconcurrency.annotation.UseCyclicBarrier;
import com.example.springbootstarterconcurrency.bean.BarrierAction;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CyclicBarrier;

@Aspect
public class UseCyclicBarrierAspect {

    private static final Logger LOG = LoggerFactory.getLogger(UseCyclicBarrierAspect.class);

    private final Map<String, CyclicBarrier> barriers = new ConcurrentHashMap<>();

    private final BarrierAction barrierAction;

    public UseCyclicBarrierAspect(BarrierAction barrierAction) {
        this.barrierAction = barrierAction;
    }

    @Pointcut("@annotation(useCyclicBarrier)")
    public void useCyclicBarrierPointcut(UseCyclicBarrier useCyclicBarrier) {
    }

    @Before("useCyclicBarrierPointcut(useCyclicBarrier)")
    public void useCyclicBarrierAdvice(ProceedingJoinPoint joinPoint, UseCyclicBarrier useCyclicBarrier) throws Throwable {

        String barrierName = useCyclicBarrier.barrier();
        var barrier = barriers.computeIfAbsent(barrierName,
                k -> new CyclicBarrier(useCyclicBarrier.parties(), barrierAction::action));

        try {

            LOG.info("Поток {} ждет у барьера.", Thread.currentThread().getName());

            barrier.await();

            LOG.info("Поток {} пересек барьер.", Thread.currentThread().getName());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
