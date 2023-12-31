package org.foi.diplomski.msakac.odmaralica.security.jwt;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.foi.diplomski.msakac.odmaralica.model.User;
import org.foi.diplomski.msakac.odmaralica.service.security.implementation.UserDetailsServiceImpl;
import org.foi.diplomski.msakac.odmaralica.service.security.implementation.UserServiceImpl;
import org.foi.diplomski.msakac.odmaralica.utils.SecurityConstants;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;


@Slf4j
@Service
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenManager jwtTokenManager;

    private final UserDetailsServiceImpl userDetailsService;

    private final UserServiceImpl userService;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {

        final String requestURI = req.getRequestURI();

        if (requestURI.contains(SecurityConstants.LOGIN_REQUEST_URI) || requestURI.contains(SecurityConstants.REGISTRATION_REQUEST_URI)) {
            chain.doFilter(req, res);
            return;
        }

        final String header = req.getHeader(SecurityConstants.HEADER_STRING);
        Long id = null;
        String authToken = null;
        if (Objects.nonNull(header) && header.startsWith(SecurityConstants.TOKEN_PREFIX)) {

            authToken = header.replace(SecurityConstants.TOKEN_PREFIX, StringUtils.EMPTY);
            try {
                id = jwtTokenManager.getUserIdFromToken(authToken);
            } catch (Exception e) {
                log.error("Authentication Exception : {}", e.getMessage());
            }
        }

        final SecurityContext securityContext = SecurityContextHolder.getContext();

        if (Objects.nonNull(id) && Objects.isNull(securityContext.getAuthentication())) {
            final User user = userService.findById(id);
            final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());

            if (jwtTokenManager.validateToken(authToken, userDetails.getUsername())) {

                final UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
                log.info("Authentication successful. Logged in email : {}. Authority: {} ", user.getEmail(), userDetails.getAuthorities());
                securityContext.setAuthentication(authentication);
            }
        }

        chain.doFilter(req, res);
    }
}
