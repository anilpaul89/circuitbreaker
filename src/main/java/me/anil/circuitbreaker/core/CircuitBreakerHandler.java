package me.anil.circuitbreaker.core;


import java.util.Optional;

public abstract class CircuitBreakerHandler {

    protected CircuitBreakerConfig circuitBreakerConfig;

    protected CircuitBreakerExecutionState executionState;

    protected void exceptionHandler(Throwable t) throws Throwable {
        Optional<Class<? extends Throwable>> exceptionFound = this.circuitBreakerConfig.getExceptions().stream().filter(test -> test.isInstance(t)).findFirst();
        if (exceptionFound.isPresent()) {
            executionState.markExceptionCaptured();
        }
    }

    protected boolean proceed() {
        return this.executionState.proceed();
    }

    protected void markRequest() {
        this.executionState.markRequest();
    }

}
