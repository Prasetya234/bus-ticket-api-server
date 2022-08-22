package com.bus.ticket.enggine.exception;

import com.bus.ticket.enggine.response.ResponseHelper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> notFoundAdviceController(NotFoundException notFoundException) {
        return ResponseHelper.errorResponse(notFoundException.getMessage(), HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name());
    }

    @ExceptionHandler(BussinesException.class)
    public ResponseEntity<?> bussinesExceprionAdviceController(BussinesException bussinesException) {
        return ResponseHelper.errorResponse(bussinesException.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.name());
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<?> bussinesExceprionAdviceController(UsernameNotFoundException usernameNotFoundException) {
        return ResponseHelper.errorResponse(usernameNotFoundException.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.name());
    }
}