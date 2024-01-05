package com.example.managementcompetitii.exception;

public class DuplicateParticipaException extends RuntimeException{
    public DuplicateParticipaException(){
        super("Sportivul a fost deja inscris la aceasta proba din cadrul competitiei alese");
    }
}
