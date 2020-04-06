package app.infy.util.service.impl;

import org.springframework.stereotype.Service;

import app.infy.util.entity.EmployeeDetail;
import app.infy.util.helper.StatusEnum;
import app.infy.util.model.ShuttleBookingStatus;
import app.infy.util.model.ShuttleRequest;
import app.infy.util.repository.AbstractEmployeeRepository;
import app.infy.util.service.ShuttleService;

@Service
public class ShuttleServiceImpl implements ShuttleService {

	private AbstractEmployeeRepository<EmployeeDetail, Integer> employeeRepository;
	
	public ShuttleServiceImpl(AbstractEmployeeRepository<EmployeeDetail, Integer> employeeRepository) {
		this.employeeRepository = employeeRepository;
	}
	
	@Override
	public String prepareShuttleRequest(ShuttleRequest shuttleRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ShuttleBookingStatus getShuttleStatusById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateShuttleBookingStatus(String shuttleRequestId, StatusEnum statusEnum) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void processShuttleRequests() {
		// TODO Auto-generated method stub

	}

}
