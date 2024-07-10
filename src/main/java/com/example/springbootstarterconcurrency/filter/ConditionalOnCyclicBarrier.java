package com.example.springbootstarterconcurrency.filter;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Optional;

public class ConditionalOnCyclicBarrier implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        return canUseBarrier(context.getEnvironment());
    }

    private boolean canUseBarrier(Environment environment) {
        var enabled = Optional.ofNullable(
                environment.getProperty("concurrency.enabled"));
        var cyclicBarrierEnabled = Optional.ofNullable(
                environment.getProperty("concurrency.cyclic-barrier-enabled"));

        boolean hasProperties = enabled.isPresent() && cyclicBarrierEnabled.isPresent();

        return hasProperties && Boolean.parseBoolean(cyclicBarrierEnabled.get());
    }

}
