package me.anil.circuitbreaker.core.strategy;

public class CountBasedCircuitBreakerStrategy implements CircuitBreakerStrategy {
    @Override
    public boolean proceed() {
        return false;
    }
}
