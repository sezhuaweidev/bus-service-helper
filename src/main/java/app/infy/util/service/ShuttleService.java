package app.infy.util.service;

import java.util.List;

import app.infy.util.entity.InfyDc;
import app.infy.util.entity.ShuttleTiming;
import app.infy.util.helper.StatusEnum;
import app.infy.util.model.ShuttleBookingStatus;
import app.infy.util.model.FormShuttleRequest;

public interface ShuttleService {

	String prepareShuttleRequest(FormShuttleRequest shuttleRequest);

	ShuttleBookingStatus getShuttleStatusById(String id);

	String updateShuttleBookingStatus(String shuttleRequestId, StatusEnum statusEnum);
	
	void processShuttleRequests();

	List<ShuttleTiming> getAllShuttles();

	List<InfyDc> getAllInfyDcs();
}
