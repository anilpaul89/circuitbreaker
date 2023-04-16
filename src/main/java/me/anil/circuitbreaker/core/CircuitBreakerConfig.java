package me.anil.circuitbreaker.core;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.With;

import java.util.Collections;
import java.util.List;

import static me.anil.circuitbreaker.utils.CircuitBreakerConfigDefaults.*;

@Data
public class CircuitBreakerConfig {

    /**
     * Time that which circuit breaker wait before transitioning
     * to half open
     */
    private int waitDurationInOpenState = defaultWaitDurationInOpenState;

    /**
     * Number of calls permitted in half open state before moving to open/closed state
     */
    private int permittedNumberOfCallsInHalfState = defaultPermittedNumberOfCallsInHalfState;

    /**
     * Sliding window algorithm used for the circuit breaker
     */
    private CircuitBreakerAlgo slidingWindowType = CircuitBreakerAlgo.COUNT_BASED;

    /**
     * Sliding window size used for the circuit breaker
     */
    private int slidingWindowSize = defaultSlidingWindowSize;

    /**
     * List of Exceptions for which we need to track and enable exceptions
     */
    private List<Class<? extends Throwable>> exceptions = Collections.emptyList();
}
