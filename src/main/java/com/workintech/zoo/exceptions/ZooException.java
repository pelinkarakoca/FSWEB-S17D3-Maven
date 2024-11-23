package com.workintech.zoo.exceptions;
import lombok.Data;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Data
public class ZooException extends RuntimeException{
	private HttpStatus HttpStatus;

	public ZooException(String message, HttpStatus HttpStatus){
		super(message);
		this.HttpStatus = HttpStatus;
	}
}
