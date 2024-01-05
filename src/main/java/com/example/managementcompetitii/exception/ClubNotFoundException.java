package com.example.managementcompetitii.exception;

public class ClubNotFoundException extends RuntimeException{
    public ClubNotFoundException(long id){
        super("Clubul cu id " + id + " nu exista");
    }
}
