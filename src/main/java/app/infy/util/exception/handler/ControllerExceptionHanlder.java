package app.infy.util.exception.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import app.infy.util.exception.ControllerException;

@ControllerAdvice
public class ControllerExceptionHanlder {

	@ExceptionHandler(ControllerException.class)
	public ResponseEntity<String> handlerControllerException(ControllerException ce) {
		return ResponseEntity.badRequest().body(ce.getMessage());
	}
	
}
