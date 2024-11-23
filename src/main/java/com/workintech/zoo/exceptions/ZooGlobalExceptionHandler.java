package com.workintech.zoo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ZooGlobalExceptionHandler {

	@ExceptionHandler(ZooException.class)
	public ResponseEntity<ZooErrorResponse> handleZooException(ZooException zooException){
	ZooErrorResponse response = new ZooErrorResponse(zooException.getHttpStatus().value(), zooException.getMessage(),System.currentTimeMillis());
		return new ResponseEntity<>(response, zooException.getHttpStatus());
	}

	@ExceptionHandler
	public ResponseEntity<ZooErrorResponse> handleZooException(Exception exception){
		ZooErrorResponse response = new ZooErrorResponse(HttpStatus.BAD_REQUEST.value(),exception.getMessage(), System.currentTimeMillis());
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

}
