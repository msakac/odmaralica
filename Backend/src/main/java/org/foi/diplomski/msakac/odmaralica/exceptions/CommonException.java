package org.foi.diplomski.msakac.odmaralica.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;


@Getter
@RequiredArgsConstructor
public class CommonException extends RuntimeException {

    private final String errorMessage;
    private final HttpStatus status;
}
