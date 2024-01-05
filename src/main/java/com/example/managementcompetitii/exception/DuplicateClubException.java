package com.example.managementcompetitii.exception;

public class DuplicateClubException extends RuntimeException{
    public DuplicateClubException() {
        super("Deja exista un club cu acest numar de inregistrare.");
    }
}
