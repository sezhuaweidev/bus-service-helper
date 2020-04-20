package app.infy.util.exception.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import app.infy.util.exception.ControllerException;
import app.infy.util.exception.PasswordInvalidException;
import app.infy.util.exception.UnwantedAccessException;
import app.infy.util.exception.UserAlreadyLoggedInException;
import app.infy.util.exception.UsernameNotFoundException;
import app.infy.util.helper.AppResponse;

@ControllerAdvice
public class ControllerExceptionHanlder {

	@ExceptionHandler(ControllerException.class)
	public ResponseEntity<String> handlerControllerException(ControllerException ce) {
		return ResponseEntity.badRequest().body(ce.getMessage());
	}
	@ExceptionHandler(PasswordInvalidException.class)
	public ResponseEntity<String> handlerWrongPasswordControllerException(PasswordInvalidException ce) {
		return ResponseEntity.badRequest().body(ce.getMessage());
	}
	@ExceptionHandler(UnwantedAccessException.class)
	public ResponseEntity<String> handleUnwantedAccessControllerException(UnwantedAccessException ce) {
		return ResponseEntity.badRequest().body(ce.getMessage());
	}
	@ExceptionHandler(UserAlreadyLoggedInException.class)
	public ResponseEntity<String> handlerAlreadyLoggedInControllerException(UserAlreadyLoggedInException ce) {
		return ResponseEntity.badRequest().body(ce.getMessage());
	}
	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<String> handlerUsernameNotFoundControllerException(UsernameNotFoundException ce) {
		return ResponseEntity.badRequest().body(ce.getMessage());
	}
}
