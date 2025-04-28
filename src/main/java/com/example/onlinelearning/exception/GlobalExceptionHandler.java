package com.example.onlinelearning.exception;

import com.example.onlinelearning.response.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
//        List<String> errors = ex.getBindingResult().getFieldErrors()
//                .stream()
//                .map(error -> error.getField() + ": " + error.getDefaultMessage())
//                .collect(Collectors.toList());
        List<String> errors = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            String message = error.getField() + ": " + error.getDefaultMessage();
            errors.add(message);
        }

        return ResponseEntity.badRequest().body(new ErrorResponse(errors));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException ex){
        Map<String,String> mess = new HashMap<>();
        mess.put("message",ex.getMessage());
        return ResponseEntity.badRequest().body(mess);
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<?> handleIOException(IOException ex){
        Map<String,String> mess = new HashMap<>();
        mess.put("message",ex.getMessage());
        return ResponseEntity.badRequest().body(mess);
    }

    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<?> handleIOException(FileNotFoundException ex){
        Map<String,String> mess = new HashMap<>();
        mess.put("message",ex.getMessage());
        return ResponseEntity.badRequest().body(mess);
    }
}
