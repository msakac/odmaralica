package org.foi.diplomski.msakac.odmaralica.model.security;

public enum TokenType {
    Activation("activation"),
    PasswordReset("password_reset");

    private final String value;

    TokenType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
