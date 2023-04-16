package me.anil.circuitbreaker;


import java.util.function.Supplier;

public interface CircuitBreaker {

    <T> T execute(Supplier<T> supplier) throws Throwable;

    <T> T execute(Supplier<T> supplier, Supplier<T> fallback) throws Throwable;

    void reset();
}
