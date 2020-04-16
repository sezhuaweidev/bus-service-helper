package app.infy.util.service;

import app.infy.util.model.LoginObject;

public interface AuthenticationService {

	String createSession(LoginObject loginObject);

}
