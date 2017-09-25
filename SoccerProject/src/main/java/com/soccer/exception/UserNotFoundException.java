package com.soccer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="No user login") 
public class UserNotFoundException extends RuntimeException{

	/**
	 * Auto generated serial version UID
	 */
	private static final long serialVersionUID = -8406588410892277069L;	 
}
