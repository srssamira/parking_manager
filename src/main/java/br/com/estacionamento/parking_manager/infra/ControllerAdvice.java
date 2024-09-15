package br.com.estacionamento.parking_manager.infra;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class) {
    @ResponseStatus(HttpStatus.)
    }
}
