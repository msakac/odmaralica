package org.foi.diplomski.msakac.odmaralica.exceptions;

import org.foi.diplomski.msakac.odmaralica.dto.common.CreateResponseDTO;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;

public final class InvalidRequestResponseBuilder {
    public String message;

    public static CreateResponseDTO<Object> createResponse(Exception e) {
        if (e instanceof DataIntegrityViolationException){
            String message = buildDataIntegrityViolationExceptionMessage((DataIntegrityViolationException) e);
            return new CreateResponseDTO<Object>(HttpStatus.BAD_REQUEST, message);
        }
        else if(e instanceof IllegalArgumentException){
            String message = buildIllegalArgumentExceptionMessage((IllegalArgumentException) e);
            return new CreateResponseDTO<Object>(HttpStatus.BAD_REQUEST, message);
        }
        else if (e instanceof BadCredentialsException){
            return new CreateResponseDTO<Object>(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
        //TODO: RuntimeException zamijeniti sa svojim exceptionom
        else if (e instanceof RuntimeException){
            return new CreateResponseDTO<Object>(HttpStatus.CONFLICT, e.getMessage());
        }

        return new CreateResponseDTO<Object>(HttpStatus.BAD_REQUEST, "Invalid request.");
    }

    private static String buildDataIntegrityViolationExceptionMessage(DataIntegrityViolationException e) {
        return "Could not execute request. Value of field is invalid due to constraint violation.";
    }

    private static String buildIllegalArgumentExceptionMessage(IllegalArgumentException e) {
        String errorMessage = "Could not execute request. Value of field {field} is invalid.";
        String message = "";
        message =  e.getMessage();
        if(message != null && message.length() > 0){
            String [] constraintNameParts = message.split("\\[");
            String fieldName = constraintNameParts[1].split("\\]")[0];
            errorMessage = errorMessage.replace("{field}", fieldName);
        }
        return errorMessage;
    }
}
