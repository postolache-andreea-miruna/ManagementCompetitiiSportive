package com.example.managementcompetitii.exception;

public class SportivNotFoundException extends RuntimeException{
    public SportivNotFoundException(long id){
        super("Sportivul cu id "+id+" nu exista.");
    }
}
