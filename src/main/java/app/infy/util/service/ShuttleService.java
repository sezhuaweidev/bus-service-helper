package app.infy.util.service;

import java.util.List;

import app.infy.util.entity.InfyCountry;
import app.infy.util.entity.InfyDc;
import app.infy.util.entity.InfyRegion;
import app.infy.util.entity.ShuttleRequest;
import app.infy.util.entity.ShuttleTiming;
import app.infy.util.helper.StatusEnum;
import app.infy.util.model.FormShuttleRequest;
import app.infy.util.model.ShuttleBookingStatus;

public interface ShuttleService {

	String prepareShuttleRequest(FormShuttleRequest shuttleRequest);

	ShuttleBookingStatus getShuttleStatusById(String id);

	String updateShuttleBookingStatus(String shuttleRequestId, StatusEnum statusEnum);

	List<ShuttleTiming> getAllShuttles();

	List<InfyDc> getAllInfyDcs();
	List<InfyRegion> getAllInfyRegion();
	List<InfyCountry> getInfyCountry(String continent);
	List<ShuttleRequest> findShuttleRequestByMngIdAndDate(Integer approverId,String forDate);

	List<ShuttleRequest> searchShuttleRequest(String type, String value);
}
