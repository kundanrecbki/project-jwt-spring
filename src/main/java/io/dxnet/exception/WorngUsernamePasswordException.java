package io.dxnet.exception;

public class WorngUsernamePasswordException extends RuntimeException{
	private static final long serialVersionUID = -8788056568305407312L;	

	public WorngUsernamePasswordException() {
		super("Wrong Username or Passwrod entered!");
	}
}
