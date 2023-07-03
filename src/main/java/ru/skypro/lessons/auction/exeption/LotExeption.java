package ru.skypro.lessons.auction.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.sql.SQLException;

@RestControllerAdvice
public class LotExeption {
    @ExceptionHandler(LotNotFoundException.class)
    public ResponseEntity<?> handleNotFound(LotNotFoundException e){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("лот с id = %d не найден".formatted(e.getId()));
    }

}