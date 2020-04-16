package app.infy.util.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import app.infy.util.helper.MessageConstants;

public class LoginObject {
	
	@NotNull(message = MessageConstants.USERNAME_CANNOT_BE_NULL)
	@Length(message = MessageConstants.USERNAME_UNACCEPTABLE)
	private String username;
	
	@NotNull(message = MessageConstants.PASSWORD_CANNOT_BE_NULL)
	@Pattern(regexp = "((?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[\\W]).{6,20})",message = MessageConstants.PASSWORD_UNACCEPTABLE)
	private String password;
	
	@NotEmpty(message = MessageConstants.USERNAME_TYPE_CANNOT_BE_EMPTY)
	private String type;
	
	public LoginObject() {
		super();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
