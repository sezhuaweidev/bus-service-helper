package app.infy.util.repository;

public class EmployeeRestRepository {
	
	private String restRepoUri;
	private String restIdentifierToken;
	
	public EmployeeRestRepository(String restRepoUri, String restIdentifierToken) {
		super();
		this.restRepoUri = restRepoUri;
		this.restIdentifierToken = restIdentifierToken;
	}
}
