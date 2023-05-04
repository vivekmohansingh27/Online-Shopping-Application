package com.masai.Exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;


@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(OrderException.class)
	public ResponseEntity<MyErrorDetails> OrderExcetionHandler(OrderException e, WebRequest req){
		
		MyErrorDetails er= new MyErrorDetails();
		er.setTimeStamp(LocalDateTime.now());
		er.setMessage(e.getMessage());
		er.setDetails(req.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(er,HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<MyErrorDetails> AllExcetionHandler(Exception e,WebRequest req){
		
		MyErrorDetails er= new MyErrorDetails();
		er.setTimeStamp(LocalDateTime.now());
		er.setMessage(e.getMessage());
		er.setDetails(req.getDescription(false));
		return new ResponseEntity<MyErrorDetails>(er,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(CustomerException.class)
	public ResponseEntity<MyErrorDetails> customerExceptionHandller(CustomerException ce, WebRequest req ) {
		MyErrorDetails err = new MyErrorDetails();
		err.setTimeStamp(LocalDateTime.now());
		err.setMessage(ce.getMessage());
		err.setDetails(req.getDescription(false));
		return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(InputInvalidException.class)
	public ResponseEntity<MyErrorDetails> inputInvalidExceptionHandller(InputInvalidException ce, WebRequest req ) {
		MyErrorDetails err = new MyErrorDetails();
		err.setTimeStamp(LocalDateTime.now());
		err.setMessage(ce.getMessage());
		err.setDetails(req.getDescription(false));
		return new ResponseEntity<>(err,HttpStatus.NOT_ACCEPTABLE);
	}
	@ExceptionHandler(AlreadyExistedException.class)
	public ResponseEntity<MyErrorDetails> alreadyExistedExceptionHandller(AlreadyExistedException ce, WebRequest req ) {
		MyErrorDetails err = new MyErrorDetails();
		err.setTimeStamp(LocalDateTime.now());
		err.setMessage(ce.getMessage());
		err.setDetails(req.getDescription(false));
		return new ResponseEntity<>(err,HttpStatus.NOT_ACCEPTABLE);
	}
	
}
