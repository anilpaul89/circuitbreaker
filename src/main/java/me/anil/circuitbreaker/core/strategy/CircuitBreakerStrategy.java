package me.anil.circuitbreaker.core.strategy;

public interface CircuitBreakerStrategy {

    boolean proceed();
}
