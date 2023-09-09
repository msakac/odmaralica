package org.foi.diplomski.msakac.odmaralica.service.security.implementation;

import lombok.RequiredArgsConstructor;
import org.foi.diplomski.msakac.odmaralica.model.Role;
import org.foi.diplomski.msakac.odmaralica.model.User;
import org.foi.diplomski.msakac.odmaralica.model.security.CustomUser;
import org.foi.diplomski.msakac.odmaralica.model.security.OAuth2UserInfo;
import org.foi.diplomski.msakac.odmaralica.repository.RoleRepository;
import org.foi.diplomski.msakac.odmaralica.repository.UserRepository;
import org.foi.diplomski.msakac.odmaralica.security.oauth.OAuth2AuthenticationProcessingException;
import org.foi.diplomski.msakac.odmaralica.security.oauth.OAuth2UserInfoFactory;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private static final String REGISTRATION_ROLE = "user";

    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);

        try {
            return processOAuth2User(oAuth2UserRequest, oAuth2User);
        } catch (AuthenticationException ex) {
            throw ex;
        } catch (Exception ex) {
            // Throwing an instance of AuthenticationException will trigger the OAuth2AuthenticationFailureHandler
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        }
    }

    private OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {
        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(oAuth2UserRequest.getClientRegistration().getRegistrationId(), oAuth2User.getAttributes());
        if (oAuth2UserInfo.getEmail().length() == 0) {
            throw new OAuth2AuthenticationProcessingException("Email not found from OAuth2 provider");
        }

        User user = userRepository.findByEmail(oAuth2UserInfo.getEmail());
        if (user == null) {
            user = registerNewUser(oAuth2UserRequest, oAuth2UserInfo);
        }

        return CustomUser.create(user, oAuth2User.getAttributes());
    }

    private User registerNewUser(OAuth2UserRequest oAuth2UserRequest, OAuth2UserInfo oAuth2UserInfo) {
        //TODO na era modelu deti kruzice na one2many
        //TODO slika korisnika
        User user = new User();
        String name = oAuth2UserInfo.getName();
        String[] parts = name.split(" ");
        user.setName(parts[0]);
        user.setEmail(oAuth2UserInfo.getEmail());
        user.setRole(getRole());
        user.setPassword("no-password");
        user.setActivated(true);
        user.setPolicyAccepted(false);
        user.setSurname(parts[1]);
        return userRepository.save(user);
    }

    private Role getRole() {
        Role role = roleRepository.findByRole(REGISTRATION_ROLE);
        if (role == null) {
            role = new Role();
            role.setRole(REGISTRATION_ROLE);
            roleRepository.save(role);
        }
        return role;
    }

}
