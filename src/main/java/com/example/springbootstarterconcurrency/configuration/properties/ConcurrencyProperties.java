package com.example.springbootstarterconcurrency.configuration.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "concurrency")
public class ConcurrencyProperties {

    private boolean enabled;

    private boolean lockEnabled;

    private boolean cyclicBarrierEnabled;

    private boolean semaphoreEnabled;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isLockEnabled() {
        return lockEnabled;
    }

    public void setLockEnabled(boolean lockEnabled) {
        this.lockEnabled = lockEnabled;
    }

    public boolean isCyclicBarrierEnabled() {
        return cyclicBarrierEnabled;
    }

    public void setCyclicBarrierEnabled(boolean cyclicBarrierEnabled) {
        this.cyclicBarrierEnabled = cyclicBarrierEnabled;
    }

    public boolean isSemaphoreEnabled() {
        return semaphoreEnabled;
    }

    public void setSemaphoreEnabled(boolean semaphoreEnabled) {
        this.semaphoreEnabled = semaphoreEnabled;
    }
}
