package org.foi.diplomski.msakac.odmaralica.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public class CommonException extends RuntimeException {

	private final String errorMessage;
	private final HttpStatus status;
}
