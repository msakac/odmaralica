package org.foi.diplomski.msakac.odmaralica.utils;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Objects;


@Service
public class ExceptionMessageAccessor {

    private final MessageSource messageSource;

    ExceptionMessageAccessor(@Qualifier("exceptionMessageSource") MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String getMessage(String key, Object... parameter) {
        return this.getMessage(null, key, parameter);
    }

    private String getMessage(Locale locale, String key, Object... parameter) {

        if (Objects.isNull(locale)) {
            return messageSource.getMessage(key, parameter, ProjectConstants.DEFAULT_LOCALE);
        }

        return messageSource.getMessage(key, parameter, locale);
    }

}
