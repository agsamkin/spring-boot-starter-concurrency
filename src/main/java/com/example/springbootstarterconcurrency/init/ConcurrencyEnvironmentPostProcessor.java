package com.example.springbootstarterconcurrency.init;

import com.example.springbootstarterconcurrency.exception.ConcurrencyStartupException;

import org.apache.commons.logging.Log;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.boot.logging.DeferredLogFactory;
import org.springframework.core.env.ConfigurableEnvironment;

public class ConcurrencyEnvironmentPostProcessor implements EnvironmentPostProcessor {

    private final Log log;

    public ConcurrencyEnvironmentPostProcessor(DeferredLogFactory deferredLogFactory) {
        this.log = deferredLogFactory.getLog(ConcurrencyEnvironmentPostProcessor.class);
    }

    @Override
    public void postProcessEnvironment(
            ConfigurableEnvironment environment, SpringApplication application
    ) {
        log.info("Вызов ConcurrencyEnvironmentPostProcessor.class");
        String enabledPropertyValue = environment.getProperty("concurrency.enabled");
        boolean isBooleanValue = Boolean.TRUE.toString().equals(enabledPropertyValue)
                 || Boolean.FALSE.toString().equals(enabledPropertyValue);

        if (!isBooleanValue) {
            throw new ConcurrencyStartupException("Ошибка при проверке свойства 'concurrency.enabled'");
        }
    }

}
