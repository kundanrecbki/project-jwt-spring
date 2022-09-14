package io.dxnet.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHelper {

	@ExceptionHandler(value = { UserNameAlreadyExistsException.class })
	@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
	public ResponseEntity<?> handleUserNameAlreadyExistsException(UserNameAlreadyExistsException ex) {
		log.error("UserNameAlreadyExistsException:", ex.getMessage());
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_ACCEPTABLE);
	}

	@ExceptionHandler(value = { WorngUsernamePasswordException.class })
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<?> handleUsernamePasswordWrongException(WorngUsernamePasswordException ex) {
		log.error("UsernamePasswordWrongException:", ex.getMessage());
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = {MethodArgumentNotValidException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	ResponseEntity<String> handleConstraintViolationException(MethodArgumentNotValidException ex) {
		log.error("MethodArgumentNotValidException:", ex.getMessage());
		return new ResponseEntity<>("Request not valid due to validation error: " + ex.getMessage() , HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = { Exception.class })
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<?> handleAllOtherException(Exception ex) {
		log.error("INTERNAL_SERVER_ERROR, Needs to be corrected by Us:", ex);
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
