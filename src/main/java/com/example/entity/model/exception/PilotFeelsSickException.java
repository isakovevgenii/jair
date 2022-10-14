package com.example.entity.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class PilotFeelsSickException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public PilotFeelsSickException(){
        super("The pilot feels sick!");
    }
}
