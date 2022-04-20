package com.lab.client.Exceptions;

/**
 * exception class shows that route to validate contains error
 */
public class RouteValidateException extends RuntimeException {
    public RouteValidateException(String message) {
        super(message);
    }
}
