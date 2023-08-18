package org.foi.diplomski.msakac.odmaralica.security.utils;

import java.util.List;
import java.util.stream.Collectors;

import org.foi.diplomski.msakac.odmaralica.security.model.CustomUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;


public class SecurityConstants {

    // FIXME : Customize security constants for your application.

    /**
     * Token expiration time 1 days.
     */
    public static final long EXPIRATION_TIME = 24 * 60 * 60 * 1000;

    /**
     * Secret key for signature
     */
    public static final String SECRET_KEY = "mySecretKey";

    /**
     * The company who provided token.
     * You can customize issuer name, this is given as an example.
     */
    public static final String ISSUER = "www.odmaralica.com";

    /**
     * Token Prefix
     * We will use this prefix when parsing JWT Token
     */
    public static final String TOKEN_PREFIX = "Bearer ";

    /**
     * Authorization Prefix in HttpServletRequest
     * Authorization: <type> <credentials>
     * For Example : Authorization: Bearer YWxhZGxa1qea32GVuc2VzYW1l
     */
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
