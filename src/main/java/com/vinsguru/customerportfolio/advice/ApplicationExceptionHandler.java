package com.vinsguru.customerportfolio.advice;

import com.vinsguru.customerportfolio.exceptions.CustomerNotFoundException;
import com.vinsguru.customerportfolio.exceptions.GenericException;
import com.vinsguru.customerportfolio.exceptions.InsufficientBalanceException;
import com.vinsguru.customerportfolio.exceptions.InsufficientSharesException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.net.URI;
import java.util.function.Consumer;

@ControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(CustomerNotFoundException.class)
    public ProblemDetail handleException(CustomerNotFoundException ex){
        return build(HttpStatus.NOT_FOUND, ex, p -> {
            p.setType(URI.create("http://example.com/problems/customer-not-found"));
            p.setTitle("Customer Not Found");
        });
    }

    @ExceptionHandler(InsufficientBalanceException.class)
    public ProblemDetail handleException(InsufficientBalanceException ex){
        return build(HttpStatus.BAD_REQUEST, ex, p -> {
            p.setType(URI.create("http://example.com/problems/insufficient-balance"));
            p.setTitle("Insufficient Balance");
        });
    }

    @ExceptionHandler(InsufficientSharesException.class)
    public ProblemDetail handleException(InsufficientSharesException ex){
        return build(HttpStatus.BAD_REQUEST, ex, p -> {
            p.setType(URI.create("http://example.com/problems/insufficient-shares"));
            p.setTitle("Insufficient Shares");
        });
    }

    @ExceptionHandler(GenericException.class)
    public ProblemDetail handleException(GenericException ex){
        return build(HttpStatus.INTERNAL_SERVER_ERROR, ex, p -> {
            p.setType(URI.create("http://example.com/problems/generic-error"));
            p.setTitle("Generic Error");
        });
    }

    private ProblemDetail build(HttpStatus status, Exception ex, Consumer<ProblemDetail> consumer){
        var problem = ProblemDetail.forStatusAndDetail(status, ex.getMessage());
        consumer.accept(problem);
        return problem;
    }

}
