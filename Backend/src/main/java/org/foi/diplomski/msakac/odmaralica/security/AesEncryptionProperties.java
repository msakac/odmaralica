package org.foi.diplomski.msakac.odmaralica.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "aes")
public class AesEncryptionProperties {
    private String secretKey;
}
