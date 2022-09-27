package com.bus.ticket.enggine.exception;

import com.bus.ticket.enggine.response.ResponseHelper;
import com.xendit.exception.XenditException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.file.AccessDeniedException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> notFoundAdviceController(NotFoundException notFoundException) {
        return ResponseHelper.errorResponse(notFoundException.getMessage(),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BussinesException.class)
    public ResponseEntity<?> bussinesExceprionAdviceController(BussinesException bussinesException) {
        return ResponseHelper.errorResponse(bussinesException.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> handlerAccessDeniedException(final Exception ex, final HttpServletRequest request, final HttpServletResponse response) {
        return ResponseHelper.errorResponse("Access denied", HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(XenditException.class)
    public ResponseEntity<?> xenditExceprionAdviceController(XenditException xenditException) {
        return ResponseHelper.errorResponse(xenditException.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<?> bussinesExceprionAdviceController(UsernameNotFoundException usernameNotFoundException) {
        return ResponseHelper.errorResponse(usernameNotFoundException.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}