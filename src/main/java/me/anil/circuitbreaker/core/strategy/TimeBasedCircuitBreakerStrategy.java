package me.anil.circuitbreaker.core.strategy;

public class TimeBasedCircuitBreakerStrategy implements CircuitBreakerStrategy {

    @Override
    public boolean proceed() {
        return false;
    }
}
