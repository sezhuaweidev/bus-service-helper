package app.infy.util.converter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import app.infy.util.entity.ShuttleRequest;
import app.infy.util.model.FormShuttleRequest;

@Component
public class FormToShuttleRequestConverter implements Converter<FormShuttleRequest, ShuttleRequest> {

	@Override
	public ShuttleRequest convert(FormShuttleRequest source) {
		
		ShuttleRequest shuttleRequest = new ShuttleRequest();
		shuttleRequest.setShuttleId(source.getShuttleId());
		shuttleRequest.setRequestId(source.getShuttleId());
		shuttleRequest.setApprover(source.getApprover());
		shuttleRequest.setRequester(source.getRequester());
		shuttleRequest.setForDate(source.getForDate());
		shuttleRequest.setStatus("PENDING");
		shuttleRequest.setReason(source.getReason());
		shuttleRequest.setRequestId(getRequestId(source.getRequester()));
		return shuttleRequest;
	}

	private String getRequestId(Integer requesterId) {
		String idSuffix = LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddMMyyyy"));
		return "STLREQ"+requesterId+idSuffix;
	}

}
