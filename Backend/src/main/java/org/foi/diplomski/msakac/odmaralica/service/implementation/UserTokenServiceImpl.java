package org.foi.diplomski.msakac.odmaralica.service.implementation;

import org.foi.diplomski.msakac.odmaralica.model.security.UserToken;
import org.foi.diplomski.msakac.odmaralica.repository.UserTokenRepository;
import org.foi.diplomski.msakac.odmaralica.service.security.IUserTokenService;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserTokenServiceImpl implements IUserTokenService {

    private final UserTokenRepository userTokenRepository;

    @Override
    public UserToken create(UserToken refreshToken) {
        return userTokenRepository.save(refreshToken);
    }

    @Override
    public UserToken update(UserToken refreshToken) {
        return userTokenRepository.save(refreshToken);
    }
    
}
