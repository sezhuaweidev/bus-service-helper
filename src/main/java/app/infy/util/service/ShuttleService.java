package app.infy.util.service;

import app.infy.util.helper.StatusEnum;
import app.infy.util.model.ShuttleBookingStatus;
import app.infy.util.model.ShuttleRequest;

public interface ShuttleService {

	String prepareShuttleRequest(ShuttleRequest shuttleRequest);

	ShuttleBookingStatus getShuttleStatusById(String id);

	String updateShuttleBookingStatus(String shuttleRequestId, StatusEnum statusEnum);
	
	void processShuttleRequests();
}
