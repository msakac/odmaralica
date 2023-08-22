package org.foi.diplomski.msakac.odmaralica.exceptions;

public class AccountNotActivatedException extends RuntimeException {
    public AccountNotActivatedException(String msg) {
        super(msg);
    }
}
