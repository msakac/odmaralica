package org.foi.diplomski.msakac.odmaralica.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import org.foi.diplomski.msakac.odmaralica.model.Role;
import org.foi.diplomski.msakac.odmaralica.model.User;
import org.foi.diplomski.msakac.odmaralica.model.security.CustomUser;
import org.foi.diplomski.msakac.odmaralica.model.security.RefreshToken;
import org.foi.diplomski.msakac.odmaralica.service.security.implementation.RefreshTokenServiceImpl;
import org.foi.diplomski.msakac.odmaralica.service.security.implementation.UserServiceImpl;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;


@Component
@RequiredArgsConstructor
public class JwtTokenManager {

    private final JwtProperties jwtProperties;
    private final UserServiceImpl userService;
    private final RefreshTokenServiceImpl refreshTokenService;

    public String generateToken(User user) {

        final Long id = user.getId();
        final Role userRole = user.getRole();

        return JWT.create()
                .withSubject(Long.toString(id))
                .withIssuer(jwtProperties.getIssuer())
                .withClaim("role", userRole.getRole())
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + jwtProperties.getExpirationMinute() * 60 * 1000))
                .sign(Algorithm.HMAC256(jwtProperties.getSecretKey().getBytes()));
    }

    public String generateToken(Authentication authentication) {

        CustomUser customUser = (CustomUser) authentication.getPrincipal();
        final Long id = customUser.getId();
        final User user = userService.findById(id);
        final Role userRole = user.getRole();

        return JWT.create()
                .withSubject(Long.toString(id))
                .withIssuer(jwtProperties.getIssuer())
                .withClaim("role", userRole.getRole())
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + jwtProperties.getExpirationMinute() * 60 * 1000))
                .sign(Algorithm.HMAC256(jwtProperties.getSecretKey().getBytes()));
    }

    public String generateRefreshToken(User user) {
        final Long id = user.getId();
        final long expiresAt = System.currentTimeMillis() + jwtProperties.getRefreshExpirationMinute() * 60 * 1000;
        String token = JWT.create()
                .withSubject(Long.toString(id))
                .withClaim("refreshToken", true)
                .withIssuer(jwtProperties.getIssuer())
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(expiresAt))
                .sign(Algorithm.HMAC256(jwtProperties.getRefreshSecretKey().getBytes()));

        RefreshToken refreshToken = RefreshToken.builder()
                .token(token)
                .user(user)
                .expiresAt(new Timestamp(expiresAt))
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .isExpired(false)
                .build();
        refreshTokenService.deactivateOldToken(id);
        refreshTokenService.create(refreshToken);
        return token;
    }

    public Long getUserIdFromToken(String token) {

        final DecodedJWT decodedJWT = getDecodedJWT(token);

        return Long.parseLong(decodedJWT.getSubject());
    }

    public boolean validateToken(String token, String authenticatedEmail) {

        final Long idFromToken = getUserIdFromToken(token);
        final User user = userService.findById(idFromToken);
        final boolean equalsEmail = authenticatedEmail.equals(user.getEmail());
        final boolean tokenExpired = isTokenExpired(token);

        return equalsEmail && !tokenExpired;
    }

    public boolean isTokenExpired(String token) {

        final Date expirationDateFromToken = getExpirationDateFromToken(token);
        return expirationDateFromToken.before(new Date());
    }

    private Date getExpirationDateFromToken(String token) {

        final DecodedJWT decodedJWT = getDecodedJWT(token);

        return decodedJWT.getExpiresAt();
    }

    private DecodedJWT getDecodedJWT(String token) {

        final JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(jwtProperties.getSecretKey().getBytes())).build();

        return jwtVerifier.verify(token);
    }

    public Long getUserIdFromRefreshToken(String token) {

        final DecodedJWT decodedJWT =  getDecodedJWTRefreshToken(token);

        return Long.parseLong(decodedJWT.getSubject());
    }


    public boolean isRefreshTokenExpired(String token) {
        DecodedJWT decodedJWT = getDecodedJWTRefreshToken(token);
        Date expirationDateFromToken = decodedJWT.getExpiresAt();
        return expirationDateFromToken.before(new Date());
    }

    private DecodedJWT getDecodedJWTRefreshToken(String token) {

        final JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(jwtProperties.getRefreshSecretKey().getBytes())).build();

        return jwtVerifier.verify(token);
    }

}
