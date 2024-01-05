package com.example.managementcompetitii.exception;

import com.example.managementcompetitii.model.ParticipaId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({ DuplicateTipException.class})
    public ResponseEntity handle(DuplicateTipException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler({ DuplicateProbaException.class})
    public ResponseEntity handle(DuplicateProbaException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler({ DuplicateClubException.class})
    public ResponseEntity handle(DuplicateClubException e){
        return ResponseEntity.status(500).body(e.getMessage());
    }
    @ExceptionHandler({ DuplicateCompetitieException.class})
    public ResponseEntity handle(DuplicateCompetitieException e){
        return ResponseEntity.status(500).body(e.getMessage());
    }
    @ExceptionHandler({ DuplicateSportivException.class})
    public ResponseEntity handle(DuplicateSportivException e){
        return ResponseEntity.status(500).body(e.getMessage());
    }
    @ExceptionHandler({ DuplicateParticipaException.class})
    public ResponseEntity handle(DuplicateParticipaException e){
        return ResponseEntity.status(500).body(e.getMessage());
    }
    @ExceptionHandler({ ClubNotFoundException.class})
    public ResponseEntity handle(ClubNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
    @ExceptionHandler({ AntrenorNotFoundException.class})
    public ResponseEntity handle(AntrenorNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
    @ExceptionHandler({ CompetitieNotFoundException.class})
    public ResponseEntity handle(CompetitieNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
    @ExceptionHandler({ SportivNotFoundException.class})
    public ResponseEntity handle(SportivNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
    @ExceptionHandler({ SportivAndCompetitieNotFoundException.class})
    public ResponseEntity handle(SportivAndCompetitieNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
    @ExceptionHandler({ ParticipaNotFoundException.class})
    public ResponseEntity handle(ParticipaNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
//pentru validare fielduri
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, Object> errors = new LinkedHashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", Instant.now());
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("errors", errors);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

}
