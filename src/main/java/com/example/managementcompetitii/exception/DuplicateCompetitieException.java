package com.example.managementcompetitii.exception;

public class DuplicateCompetitieException extends RuntimeException{
    public DuplicateCompetitieException(){
        super("Exista competitie cu acelasi nume");
    }
}
