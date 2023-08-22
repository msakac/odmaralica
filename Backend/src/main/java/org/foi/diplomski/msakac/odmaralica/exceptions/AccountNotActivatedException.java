package org.foi.diplomski.msakac.odmaralica.exceptions;

public class AccountNotActivatedException extends RuntimeException {
    public AccountNotActivatedException(String email) {
        super("Account with email '" + email + "' is not activated.");
    }
}
