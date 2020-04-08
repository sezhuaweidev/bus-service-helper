package app.infy.util.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import app.infy.util.exception.ApplicationException;
import app.infy.util.helper.AppResponse;
import app.infy.util.helper.MessageConstants;

@RestControllerAdvice
public class ApplicationExceptionHandler {
	
	@ExceptionHandler(ApplicationException.class)
	@ResponseStatus(code=HttpStatus.BAD_REQUEST)
	public AppResponse<String> handleApplicationException(ApplicationException ape) {
		return new AppResponse<String>(ape.getMessage());
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseStatus(code=HttpStatus.BAD_REQUEST)
	public AppResponse<String> handleMessageNotException(HttpMessageNotReadableException exp) {
		return new AppResponse<String>(MessageConstants.INPUT_DATA_MALFORMED);
	}
	
}
