package org.foi.diplomski.msakac.odmaralica.utils;

import java.util.List;
import java.util.stream.Collectors;

import org.foi.diplomski.msakac.odmaralica.model.security.CustomUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;


public class SecurityConstants {

    public static final Long ACTIVATION_TOKEN_EXPIRATION_TIME = 24 * 60 * 60 * 1000L; // 24 hours

    public static final Long PASSWORD_RESET_TOKEN_EXPIRATION_TIME = 20 * 60 * 1000L; // 20 minutes

    public static final String TOKEN_PREFIX = "Bearer ";

    public static final String HEADER_STRING = "Authorization";

    public static final String LOGIN_REQUEST_URI = "/login";

    public static final String REGISTRATION_REQUEST_URI = "/register";


    private SecurityConstants() {

        throw new UnsupportedOperationException();
    }

    /**
     * @return authenticated username from Security Context
     */
    public static String getAuthenticatedUsername() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                UserDetails userDetails = (UserDetails) principal;
                return userDetails.getUsername();
            }
        }
        return ""; // Return empty string if UserDetails is not available or principal is not of expected type
    }


    public static String getAuthenticatedUserRole() {

        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream().map(Object::toString).collect(Collectors.toList());
        return roles.get(0);
    }

    public static Long getAuthenticatedUserId() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            CustomUser userDetails = (CustomUser) authentication.getPrincipal();
            return userDetails.getId();
        }
        return null;
    }

}
