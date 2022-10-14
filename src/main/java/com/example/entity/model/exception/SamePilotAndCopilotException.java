package com.example.entity.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class SamePilotAndCopilotException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public SamePilotAndCopilotException() {
        super("The pilot and co-pilot are the same person!");
    }

}
