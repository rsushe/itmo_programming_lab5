package com.lab.client.Exceptions;

/**
 * exception class shows that file cannot be read
 */
public class FileReadPermissionException extends RuntimeException {
    public FileReadPermissionException(String message) {
        super(message);
    }
}
