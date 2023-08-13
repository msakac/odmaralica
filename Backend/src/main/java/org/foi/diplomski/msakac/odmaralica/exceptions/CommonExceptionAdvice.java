package org.foi.diplomski.msakac.odmaralica.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;


@RestControllerAdvice
public class CommonExceptionAdvice {

    @ExceptionHandler(CommonException.class)
    ResponseEntity<ApiExceptionResponse> handleCommonException(CommonException exception) {

        final ApiExceptionResponse response = new ApiExceptionResponse(exception.getErrorMessage(), exception.getStatus(), LocalDateTime.now());

        return ResponseEntity.status(response.getStatus()).body(response);
    }

}
