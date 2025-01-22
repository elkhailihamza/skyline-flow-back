package org.project.skyflow.exception;

public class ItemNotOwnedException extends RuntimeException {
    public ItemNotOwnedException(String message) {
        super(message);
    }
}
