package org.foi.diplomski.msakac.odmaralica.utils;

import lombok.Getter;

@Getter
public enum Operator {
    NOT_EQUAL("!="),
    GREATER_THAN(">"),
    LESS_THAN("<"),
    IN("~in="),
    EQUAL("=");


    private final String symbol;

    Operator(String symbol) {
        this.symbol = symbol;
    }

    public static Operator findOperator(String key) {
        for (Operator operator : values()) {
            if (key.contains(operator.getSymbol())) {
                return operator;
            }
        }
        return null;
    }
}


