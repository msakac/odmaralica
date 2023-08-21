package org.foi.diplomski.msakac.odmaralica.service.security;

import org.foi.diplomski.msakac.odmaralica.model.security.RefreshToken;

public interface IRefreshTokenService {
    RefreshToken create(RefreshToken refreshToken);
    RefreshToken update(RefreshToken refreshToken);
}
