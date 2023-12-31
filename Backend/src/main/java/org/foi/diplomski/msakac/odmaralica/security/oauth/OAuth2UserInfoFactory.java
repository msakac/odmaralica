package org.foi.diplomski.msakac.odmaralica.security.oauth;

import org.foi.diplomski.msakac.odmaralica.model.security.OAuth2UserInfo;

import java.util.Map;

import static org.foi.diplomski.msakac.odmaralica.security.oauth.AuthProvider.google;

public class OAuth2UserInfoFactory {

    public static OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) {
        if (registrationId.equalsIgnoreCase(google.toString())) {
            return new GoogleOAuth2UserInfo(attributes);
        } else {
            throw new OAuth2AuthenticationProcessingException("Sorry! Login with " + registrationId + " is not supported yet.");
        }
    }
}
