package com.rakoon.backend.exception;
public class InvalidDatatypeException extends RuntimeException{
    public InvalidDatatypeException(String message) {
        super(message);
    }
}