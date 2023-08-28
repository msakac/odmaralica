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

        InMemoryClientRegistrationRepository clientRegistrationRepository = new InMemoryClientRegistrationRepository(clientRegistration);

        System.out.println(clientRegistrationRepository);

        return new InMemoryClientRegistrationRepository(clientRegistration);
    }
}

