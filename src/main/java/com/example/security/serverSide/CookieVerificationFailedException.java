package com.example.security.serverSide;

public class CookieVerificationFailedException extends RuntimeException {
    public CookieVerificationFailedException(String message) {
        super(message);
    }
}