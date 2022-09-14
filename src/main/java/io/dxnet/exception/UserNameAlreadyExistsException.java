package io.dxnet.exception;

public class UserNameAlreadyExistsException extends RuntimeException{

	private static final long serialVersionUID = -6887368060292649913L;

	public UserNameAlreadyExistsException(String username) {
		super(String.format("A user with Username: %s, already exist, please select a different username", username));
	}
}
