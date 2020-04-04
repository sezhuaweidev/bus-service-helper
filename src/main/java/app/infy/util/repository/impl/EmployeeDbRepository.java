package app.infy.util.repository.impl;

import java.util.Set;

import org.springframework.jdbc.core.JdbcTemplate;

import app.infy.util.entity.EmployeeDetail;
import app.infy.util.repository.AbstractEmployeeRepository;

public class EmployeeDbRepository extends AbstractEmployeeRepository<EmployeeDetail, Integer> {

	private JdbcTemplate jdbcTemplate;
	
	public EmployeeDbRepository( JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
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
