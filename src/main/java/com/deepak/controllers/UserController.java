package com.deepak.controllers;

import com.deepak.models.User;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeoutException;

@RestController
public class UserController {
    
    private static final String USER_SERVICE = "userService";
    
    // Success
    @GetMapping("success/users")
    @CircuitBreaker(name = USER_SERVICE, fallbackMethod = "testFallBack")
    public Mono<ResponseEntity<User>> getUser(@PathVariable String id) {
        return Mono.just(ResponseEntity.ok(User.builder().id("1").name("Deepak").build()));
    }
    
    // Failure
    @GetMapping("failure/users")
    @CircuitBreaker(name = USER_SERVICE)
    public Mono<ResponseEntity<User>> getUserWithFailure() {
        // This will cause the circuit breaker to go in open state.
        return Mono.error(new TimeoutException("Timeout exception occurred."));
        
    }
    
    // With Fallback
    @GetMapping("/fallback/users")
    @CircuitBreaker(name = USER_SERVICE, fallbackMethod = "getUserFallback")
    public Mono<ResponseEntity<User>> getUserWithFallback() {
        // This will cause the exception and control will be transferred to fallback method.
        return Mono.error(new TimeoutException("Timeout exception occurred."));
    }
    
    private Mono<ResponseEntity<User>> getUserFallback() {
        // You can fetch the value from cache here when the service is down, if the
        // fallback method also cause any exception the circuit breaker will go in
        // open state accordingly after multiple failed requests(Please check
        // configurations for that).
        return Mono.just(ResponseEntity.ok(User.builder().id("1").name("Deepak").build()));
    }
}
