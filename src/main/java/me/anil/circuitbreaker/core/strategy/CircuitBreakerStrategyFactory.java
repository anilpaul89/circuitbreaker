package me.anil.circuitbreaker.core.strategy;

import me.anil.circuitbreaker.core.CircuitBreakerAlgo;

public class CircuitBreakerStrategyFactory {

    private static CircuitBreakerStrategy countBaseStrategy = new CountBasedCircuitBreakerStrategy();

    private static CircuitBreakerStrategy timeBasedStrategy = new TimeBasedCircuitBreakerStrategy();

    public static CircuitBreakerStrategy getCircuitBreakerStrategy(CircuitBreakerAlgo algo) {
        CircuitBreakerStrategy strategy = null;
        switch (algo) {
            case TIME_BASED:
                strategy = timeBasedStrategy;
                break;
            default:
                strategy = countBaseStrategy;
                break;
        }
        return strategy;
    }
}
