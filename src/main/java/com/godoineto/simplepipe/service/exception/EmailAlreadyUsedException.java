package com.godoineto.simplepipe.service.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Email is already in use")
public class EmailAlreadyUsedException extends RuntimeException {
}
