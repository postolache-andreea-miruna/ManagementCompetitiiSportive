package com.example.managementcompetitii.exception;

public class DuplicateTipException extends RuntimeException{
    public DuplicateTipException(){
        super("Acest tip de competitie a fost deja introdus.");
    }
}
