package org.foi.diplomski.msakac.odmaralica.service.security;

import org.foi.diplomski.msakac.odmaralica.model.security.UserToken;

public interface IUserTokenService {
    UserToken create(UserToken refreshToken);

    UserToken update(UserToken refreshToken);

    boolean deactivateAndCreateActivationToken(UserToken userToken);

    UserToken findByToken(String token);
}
