package com.example.SecurityPractice.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;

@RestControllerAdvice
public class GlobalExceptionHandling {

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleSecurityException(Exception ex)
    {
        ProblemDetail problemDetail=null;
      if(ex instanceof BadCredentialsException)
      {
          problemDetail=ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(401), ex.getMessage());
          problemDetail.setProperty("access_denied_reason","Authentication Failure");
      }

      if(ex instanceof AccessDeniedException)
      {
          problemDetail=ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), ex.getMessage());
          problemDetail.setProperty("access_denied_reason","Not Authorized");
      }

      return problemDetail;
    }
}
