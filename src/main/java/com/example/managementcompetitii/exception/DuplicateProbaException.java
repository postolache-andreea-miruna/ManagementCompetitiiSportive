package com.example.managementcompetitii.exception;

public class DuplicateProbaException extends RuntimeException {
    public DuplicateProbaException() {
        super("Acesta proba a fost deja introdusa.");
    }
}
