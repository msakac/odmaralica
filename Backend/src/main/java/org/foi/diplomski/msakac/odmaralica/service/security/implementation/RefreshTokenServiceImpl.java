package org.foi.diplomski.msakac.odmaralica.service.security.implementation;

import lombok.RequiredArgsConstructor;

import org.foi.diplomski.msakac.odmaralica.model.security.RefreshToken;
import org.foi.diplomski.msakac.odmaralica.repository.RefreshTokenRepository;
import org.foi.diplomski.msakac.odmaralica.service.security.IRefreshTokenService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements IRefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshToken create(RefreshToken refreshToken) {
        return refreshTokenRepository.save(refreshToken);
    }

    public RefreshToken update(RefreshToken refreshToken) {
        return refreshTokenRepository.save(refreshToken);
    }

    public void deactivateOldToken(Long userId) {
        RefreshToken refreshToken = refreshTokenRepository.findByUserIdAndIsExpiredFalse(userId);
        if (refreshToken != null) {
            refreshToken.setExpired(true);
            refreshTokenRepository.save(refreshToken);
        }
    }
}

