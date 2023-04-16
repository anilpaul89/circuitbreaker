package me.anil.circuitbreaker.core;

import java.time.Instant;
import java.util.concurrent.atomic.AtomicInteger;

public class CircuitBreakerExecutionState {

    private CircuitBreakerConfig circuitBreakerConfig;
    private CircuitBreakerState currentState = CircuitBreakerState.CLOSED;

    private int requestCountOnHalfOpenState = 0;

    private Instant currentStateInstant = Instant.now();

    //TODO: This needs to be moved to the strategy class
    private AtomicInteger countOfExceptions = new AtomicInteger();

    //TODO: This needs to be moved to the strategy class
    private AtomicInteger countOfRequest = new AtomicInteger();

    public CircuitBreakerExecutionState(CircuitBreakerConfig circuitBreakerConfig) {
        this.circuitBreakerConfig = circuitBreakerConfig;
    }

    public void reset() {
        markStateChange(CircuitBreakerState.CLOSED);
        this.countOfExceptions = new AtomicInteger();
        this.requestCountOnHalfOpenState = 0;
    }

    public void markExceptionCaptured() {
        int count = this.countOfExceptions.incrementAndGet();
        if (count >= circuitBreakerConfig.getSlidingWindowSize()) {
            markStateChange(CircuitBreakerState.OPEN);
        }
    }

    public boolean proceed() {
        return currentState == CircuitBreakerState.CLOSED || checkForStateChange();
    }

    private boolean checkForStateChange() {
        if (currentState == CircuitBreakerState.OPEN && Instant.now().isAfter(this.currentStateInstant.plusMillis(this.circuitBreakerConfig.getWaitDurationInOpenState()))) {
            markStateChange(CircuitBreakerState.HALF_OPEN);
        } else if (currentState == CircuitBreakerState.HALF_OPEN) {
            return checkForHalfOpen();
        }
        return false;
    }

    private boolean checkForHalfOpen() {
        return requestCountOnHalfOpenState <= this.circuitBreakerConfig.getPermittedNumberOfCallsInHalfState();
    }

    private void markStateChange(CircuitBreakerState state) {
        this.currentState = state;
        this.currentStateInstant = Instant.now();
    }

    public void markRequest() {
        this.countOfRequest.incrementAndGet();
    }
}
