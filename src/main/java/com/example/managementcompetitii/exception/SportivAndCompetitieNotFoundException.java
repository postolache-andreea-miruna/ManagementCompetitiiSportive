package com.example.managementcompetitii.exception;

public class SportivAndCompetitieNotFoundException extends RuntimeException{
    public SportivAndCompetitieNotFoundException(long spId, long compId){
        super("Combinatia sportiv cu id "+spId+" competitia cu id "+compId+" nu exista");
    }
}
