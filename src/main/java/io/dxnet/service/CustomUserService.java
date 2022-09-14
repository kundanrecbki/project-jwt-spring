package io.dxnet.service;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import io.dxnet.dto.CustomUserRequestDto;
import io.dxnet.dto.CustomUserResponseDto;
import io.dxnet.entity.CustomUserEntity;
import io.dxnet.event.CustomEvent;
import io.dxnet.events.publisher.CustomEventPublisher;
import io.dxnet.exception.UserNameAlreadyExistsException;
import io.dxnet.exception.WorngUsernamePasswordException;
import io.dxnet.helper.JwtTokenHelper;
import io.dxnet.mapper.CustomUserEntityToResponseDtoMapper;
import io.dxnet.repository.CustomUserRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CustomUserService implements UserDetailsService {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenHelper tokenHelper;
	@Autowired
	private CustomUserRepository userRepo;

	@Autowired
	private PasswordEncoder pwdEncoder;
	
	@Autowired
	private CustomEventPublisher customEventPublisher;

	@Autowired
	private CustomUserEntityToResponseDtoMapper customUserEntityToDtoMapper;
	
	public String createAuthToken(CustomUserRequestDto authRequest) throws Exception {
		try {
			// First Authenticate the User
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));

		} catch (BadCredentialsException ex) {
			log.error(ex.getMessage());
			throw new WorngUsernamePasswordException();
		}
				
		final UserDetails userDetails = loadUserByUsername(authRequest.getUserName());

		final String token = tokenHelper.generateToken(userDetails);
		
		// Publish the User Logged in Event
		customEventPublisher.publishEvent(new CustomEvent("UserLoggedinEvent", authRequest.getUserName(), LocalDate.now()));
		
		return token;
	}

	public CustomUserResponseDto createUser(CustomUserRequestDto user) {
		// First Check, if user already exists
		CustomUserEntity existingUser = userRepo.findByUserName(user.getUserName()).orElse(null);
		
		if(existingUser != null) {
			throw new UserNameAlreadyExistsException(user.getUserName());
		}
		
		CustomUserEntity newUser = new CustomUserEntity();
			newUser.setUserName(user.getUserName());
			newUser.setPassword(pwdEncoder.encode(user.getPassword()));
			newUser.setCreationDate(LocalDate.now());
		
		CustomUserResponseDto createdUser = customUserEntityToDtoMapper.customUserEntityToCustomUserDto(userRepo.save(newUser));
		
		customEventPublisher.publishEvent(new CustomEvent("UserRegisteredEvent", user.getUserName(), LocalDate.now() ));
		
		return createdUser;
	}	

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		
		CustomUserEntity user = userRepo.findByUserName(userName).orElseThrow(() -> {
			throw new UsernameNotFoundException("User name: " + userName+" not found");
		});
		return new User(user.getUserName(), user.getPassword(),
				new ArrayList<>());
	}
}