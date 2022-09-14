package io.dxnet.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.dxnet.dto.CustomUserRequestDto;
import io.dxnet.dto.CustomUserResponseDto;
import io.dxnet.service.CustomEventService;
import io.dxnet.service.CustomUserService;


@RestController
@CrossOrigin
@RequestMapping(value = "/api/v1/users")
public class CustomAuthenticationController {

	@Autowired
	private CustomUserService customUserService;    

	@Autowired
	private CustomEventService customEventService;   

	@PostMapping(value = "/authenticate")
	public ResponseEntity<?> createAuthToken(@Valid @RequestBody CustomUserRequestDto authRequest) throws Exception {
		
		return new ResponseEntity<>(customUserService.createAuthToken(authRequest), HttpStatus.ACCEPTED);
	}
	
	@PostMapping(value = "/register")
	public ResponseEntity<?> createUser(@Valid @RequestBody CustomUserRequestDto user) throws Exception {
		CustomUserResponseDto createdUser = customUserService.createUser(user);
		return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/events")
	public ResponseEntity<?> findAllEvents() {
		return new ResponseEntity<>(customEventService.getAllEvents(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/{userName}/events")
	public ResponseEntity<?> findAllEvents(@PathVariable @NotBlank String userName){
		return new ResponseEntity<>(customEventService.getAllEventsByUserName(userName), HttpStatus.OK);
	}

}