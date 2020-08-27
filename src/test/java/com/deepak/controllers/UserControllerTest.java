package com.deepak.controllers;

import com.deepak.models.User;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeoutException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class UserControllerTest {
    
    private static UserController userController;
    
    @BeforeClass
    public static void setUp() {
        userController = new UserController();
    }
    
    @AfterClass
    public static void destroy() {
        if (userController != null) {
            userController = null;
        }
    }
    
    @Test
    public void shouldReturnUser() {
        Mono<ResponseEntity<User>> userMono = userController.getUser("1");
        ResponseEntity<User> user = userMono.block();
        assertNotNull(user);
        assertEquals(200, user.getStatusCode().value());
        assertEquals("1", user.getBody().getId());
        assertEquals("Deepak", user.getBody().getName());
    }
    
    @Test
    public void shouldReturnTimeoutException() {
        try {
            userController.getUserWithFailure().block();
            fail("The flow should not reach here.");
        } catch (Exception exception) {
            assertTrue(exception.getCause() instanceof TimeoutException);
            System.out.println(exception.getMessage());
            assertEquals("Timeout exception occurred.", exception.getCause().getMessage());
        }
    }
}


