package org.foi.diplomski.msakac.odmaralica.exceptions;

public class EmailAlreadyExistException extends RegistrationException {

    public EmailAlreadyExistException(String email) {
        super("Email address '" + email + "' already exists.");
    }
}

