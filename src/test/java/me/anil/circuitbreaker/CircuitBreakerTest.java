package me.anil.circuitbreaker;

import me.anil.circuitbreaker.core.CircuitBreakerConfig;

import java.io.InvalidObjectException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Supplier;

public class CircuitBreakerTest {

    public static Supplier<String> execute() {
        return () -> {
            if (Instant.now().hashCode() % 2 != 0) {
                throw new RuntimeException("Simply");
            }
            return "Hello";
        };
    }

    public static void main(String[] args) {
        CircuitBreakerConfig configuration = new CircuitBreakerConfig();
        configuration.setExceptions(Arrays.asList(RuntimeException.class));
        CircuitBreaker circuitBreaker = new DefaultCircuitBreaker(configuration);
        String result = null;
        try {
            result = circuitBreaker.execute(execute());
        } catch (Throwable e) {
            System.out.println("Testing");
        }
        String result1 = null;
        try {
            result1 = circuitBreaker.execute(execute());
        } catch (Throwable e) {
            System.out.println("Testing 1");
        }
        System.out.println(result + result1);
    }
}
