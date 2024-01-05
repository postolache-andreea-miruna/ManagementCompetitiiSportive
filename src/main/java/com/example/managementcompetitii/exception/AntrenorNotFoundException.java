package com.example.managementcompetitii.exception;

public class AntrenorNotFoundException extends RuntimeException{
    public AntrenorNotFoundException(long id){
        super("Antrenorul cu id " + id + " nu exista");
    }
}
