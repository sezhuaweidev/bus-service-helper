package app.infy.util.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import app.infy.util.entity.ShuttleRequest;
import app.infy.util.model.ShuttleBookingStatus;
import app.infy.util.repository.EmployeeDetailRepository;
import app.infy.util.repository.ShuttleTimingRepository;

@Component
public class EntityToShuttleRequestConverter implements Converter<ShuttleRequest, ShuttleBookingStatus> {
	
	@Override
	public ShuttleBookingStatus convert(ShuttleRequest source) {
		ShuttleBookingStatus sbs = new ShuttleBookingStatus();
		sbs.setRequestId(source.getRequestId());
		return null;
	}

}
