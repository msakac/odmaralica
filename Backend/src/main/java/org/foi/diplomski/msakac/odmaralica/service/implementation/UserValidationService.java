package org.foi.diplomski.msakac.odmaralica.service.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.foi.diplomski.msakac.odmaralica.exceptions.CommonException;
import org.foi.diplomski.msakac.odmaralica.repository.UserRepository;
import org.foi.diplomski.msakac.odmaralica.security.dto.RegistrationRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class UserValidationService {

    private static final String EMAIL_ALREADY_EXISTS = "Email already exists";

    private final UserRepository userRepository;


    public void validateUser(RegistrationRequest registrationRequest) {

        final String email = registrationRequest.getEmail();

        checkEmail(email);
    }

    private void checkEmail(String email) {

        final boolean existsByEmail = userRepository.existsByEmail(email);

        if (existsByEmail) {

            log.warn("{} is already being used!", email);

            throw new CommonException(EMAIL_ALREADY_EXISTS, HttpStatus.CONFLICT);
        }
    }

}
