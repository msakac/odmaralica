package org.foi.diplomski.msakac.odmaralica.security.service;

import org.foi.diplomski.msakac.odmaralica.service.UserValidationService;
import org.foi.diplomski.msakac.odmaralica.model.User;
import org.foi.diplomski.msakac.odmaralica.model.Role;
import org.foi.diplomski.msakac.odmaralica.security.dto.AuthenticatedUserDto;
import org.foi.diplomski.msakac.odmaralica.security.dto.RegistrationRequest;
import org.foi.diplomski.msakac.odmaralica.security.dto.RegistrationResponse;
import org.foi.diplomski.msakac.odmaralica.security.mapper.UserMapper;
import org.foi.diplomski.msakac.odmaralica.utils.GeneralMessageAccessor;
import org.foi.diplomski.msakac.odmaralica.utils.GenericRepository;
import org.foi.diplomski.msakac.odmaralica.repository.RoleRepository;
import org.foi.diplomski.msakac.odmaralica.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private static final String REGISTRATION_SUCCESSFUL = "registration_successful";

	private static final String REGISTRATION_ROLE = "user";

	private final UserRepository userRepository;

	private final RoleRepository roleRepository;

	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	private final UserValidationService userValidationService;

	private final GeneralMessageAccessor generalMessageAccessor;

	@Override
	public RegistrationResponse registration(RegistrationRequest registrationRequest) {

		userValidationService.validateUser(registrationRequest);

		final User user = UserMapper.INSTANCE.convertToUser(registrationRequest);
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setActivated(true);
		Role role = roleRepository.findByRole(REGISTRATION_ROLE);

		if (role == null) {
			role = new Role();
			role.setRole(REGISTRATION_ROLE);
			roleRepository.save(role);
		}
		user.setRole(role);
 		userRepository.save(user);

		final String username = registrationRequest.getEmail();
		final String registrationSuccessMessage = generalMessageAccessor.getMessage(REGISTRATION_SUCCESSFUL, username);

		log.info("{} registered successfully!", username);

		return new RegistrationResponse(registrationSuccessMessage);
	}

	@Override
	public AuthenticatedUserDto findAuthenticatedUserByEmail(String email) {
		User exampleUser = new User();
		exampleUser.setEmail(email);

		final User user = GenericRepository.findOneByExample(exampleUser, userRepository);

		return UserMapper.INSTANCE.convertToAuthenticatedUserDto(user);
	}
}
