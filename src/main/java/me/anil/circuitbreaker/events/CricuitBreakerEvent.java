package me.anil.circuitbreaker.events;

import java.util.Map;

public class CricuitBreakerEvent {

    private Status status;

    private Map<String, Object> attributes;
}
