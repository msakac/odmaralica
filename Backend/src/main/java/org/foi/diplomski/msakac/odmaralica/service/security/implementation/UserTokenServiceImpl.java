package org.foi.diplomski.msakac.odmaralica.service.security.implementation;

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
    public UserToken create(UserToken userToken) {
        return userTokenRepository.save(userToken);
    }

    @Override
    public UserToken update(UserToken userToken) {
        return userTokenRepository.save(userToken);
    }

    public boolean deactivateAndCreateActivationToken(UserToken userToken) {
        UserToken validUserActivationToken = userTokenRepository.findByUserIdAndTypeAndIsUsedFalse(userToken.getUser().getId(), userToken.getType());

        //If there is no token, create new one and return true.
        if(validUserActivationToken == null){
            this.create(userToken);
            return true;

        //If there is token but it is older than 5minutes, deactivate it and create new one and return true.
        } else if( System.currentTimeMillis() > validUserActivationToken.getCreatedAt().getTime() + 300000) {
            validUserActivationToken.setUsed(true);
            this.update(validUserActivationToken);
            this.create(userToken);
            return true;
        }
        return false;
    }

}
