package me.anil.circuitbreaker.utils;

public interface CircuitBreakerConfigDefaults {

    int defaultWaitDurationInOpenState = 10000;

    int defaultPermittedNumberOfCallsInHalfState = 10;

    int defaultSlidingWindowSize = 2;

}
