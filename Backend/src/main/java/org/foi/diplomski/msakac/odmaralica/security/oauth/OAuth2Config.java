package org.foi.diplomski.msakac.odmaralica.security.oauth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;

@Configuration
public class OAuth2Config {

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        ClientRegistration clientRegistration = ClientRegistration.withRegistrationId("google")
                .clientId("350154689491-fvonen5rv9ebivkgigh33lkan7v0vrfi.apps.googleusercontent.com")
                .clientSecret("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")
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .redirectUri("http://localhost:8080/oauth2/callback/google")
                .scope("email", "profile")
                .authorizationUri("https://accounts.google.com/o/oauth2/v2/auth")
                .tokenUri("https://www.googleapis.com/oauth2/v4/token")
                .userInfoUri("https://www.googleapis.com/oauth2/v3/userinfo")
                .userNameAttributeName("email")
                .clientName("Google")
                .build();

        InMemoryClientRegistrationRepository clientRegistrationRepository = new InMemoryClientRegistrationRepository(clientRegistration);

        System.out.println(clientRegistrationRepository);

        return new InMemoryClientRegistrationRepository(clientRegistration);
    }
}

