package app.infy.util.repository.impl;

import java.util.Set;

import app.infy.util.entity.EmployeeDetail;
import app.infy.util.repository.AbstractEmployeeRepository;

public class EmployeeRestRepository extends AbstractEmployeeRepository<EmployeeDetail, Integer> {
	
	private String restRepoUri;
	private String restIdentifierToken;
	
	public EmployeeRestRepository(String restRepoUri, String restIdentifierToken) {
		super();
		this.restRepoUri = restRepoUri;
		this.restIdentifierToken = restIdentifierToken;
	}

	@Override
	public Set<EmployeeDetail> searchEmployee(String searchBy, String searchText) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EmployeeDetail getEmployeeById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<EmployeeDetail> getAllEmployeeBy() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<EmployeeDetail> getEmployeeManagers(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
}
