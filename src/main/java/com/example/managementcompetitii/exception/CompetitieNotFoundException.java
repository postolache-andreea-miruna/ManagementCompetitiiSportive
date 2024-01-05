package com.example.managementcompetitii.exception;

public class CompetitieNotFoundException extends RuntimeException{
    public CompetitieNotFoundException(long id){
        super("Competitia cu id " + id + " nu exista");
    }
}
