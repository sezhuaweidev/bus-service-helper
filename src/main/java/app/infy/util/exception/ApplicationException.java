package app.infy.util.exception;

@SuppressWarnings("serial")
public class ApplicationException extends RuntimeException {
	
	private String[] additional;
	
	public ApplicationException(String throwableMsg, String...additional) {
		super(throwableMsg);
		this.additional = additional;
	}

	public String[] getAdditional() {
		return additional;
	}
}
