package org.foi.diplomski.msakac.odmaralica.exceptions;

public class InvalidPasswordFormatException extends RegistrationException {

    public InvalidPasswordFormatException() {
        super("Password should be atleast 8 characters!");
    }
}
