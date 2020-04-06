package app.infy.util.service;

import app.infy.util.helper.StatusEnum;
import app.infy.util.model.ShuttleBookingStatus;
import app.infy.util.model.FormShuttleRequest;

public interface ShuttleService {

	String prepareShuttleRequest(FormShuttleRequest shuttleRequest);

	ShuttleBookingStatus getShuttleStatusById(String id);

	String updateShuttleBookingStatus(String shuttleRequestId, StatusEnum statusEnum);
	
	void processShuttleRequests();
}
