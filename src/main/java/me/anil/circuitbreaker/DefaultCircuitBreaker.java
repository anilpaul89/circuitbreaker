package me.anil.circuitbreaker;

import me.anil.circuitbreaker.core.CircuitBreakerConfig;
import me.anil.circuitbreaker.core.CircuitBreakerHandler;
import me.anil.circuitbreaker.core.CircuitBreakerExecutionState;
import me.anil.circuitbreaker.exception.CircuitBreakerException;

import java.util.function.Supplier;

public class DefaultCircuitBreaker extends CircuitBreakerHandler implements CircuitBreaker {

    public DefaultCircuitBreaker(CircuitBreakerConfig circuitBreakerConfig) {
        this.circuitBreakerConfig = circuitBreakerConfig;
        this.executionState = new CircuitBreakerExecutionState(circuitBreakerConfig);
    }

    @Override
    public <T> T execute(Supplier<T> supplier) throws Throwable {
        return execute(supplier, null);
    }

    @Override
    public <T> T execute(Supplier<T> supplier, Supplier<T> fallback) throws Throwable {
        try {
            markRequest();
            if (proceed()) {
                return supplier.get();
            } else if (fallback != null) {
                return fallback.get();
            }
        } catch (Exception e) {
            exceptionHandler(e);
        }
        throw new CircuitBreakerException();
    }

    public void reset() {
        reset();
    }
}
