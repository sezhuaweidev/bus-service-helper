package app.infy.util.exception;

public class UserAlreadyLoggedInException extends RuntimeException {
	public UserAlreadyLoggedInException(String msg) {
		super(msg);
	}
}
