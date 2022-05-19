package dev.ji.employeenewnew.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmployeeHasAlreadyAddedException extends RuntimeException{
    public EmployeeHasAlreadyAddedException(String message) {
        super(message);
    }
}
