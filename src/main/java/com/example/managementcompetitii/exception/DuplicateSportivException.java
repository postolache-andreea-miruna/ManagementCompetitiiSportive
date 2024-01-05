package com.example.managementcompetitii.exception;

public class DuplicateSportivException extends RuntimeException{
    public DuplicateSportivException(){
        super("Exista sportiv care are acest numar de legitimatie");
    }
}
