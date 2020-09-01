package com.francescomalagrino.api.exxception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "there is an error")
public class CustomException extends RuntimeException {
	 public CustomException(String errorMessage, Throwable err) {
	        super(errorMessage, err);
	   
	 }
}
