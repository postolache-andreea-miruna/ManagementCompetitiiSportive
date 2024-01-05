package com.example.managementcompetitii.exception;

import com.example.managementcompetitii.model.ParticipaId;

public class ParticipaNotFoundException extends RuntimeException{
    public ParticipaNotFoundException(ParticipaId id){
        super("Participarea cu id "+id+" nu exista");
    }
}
