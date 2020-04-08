package app.infy.util.model;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

public class FormShuttleRequest {
	
	@Pattern(regexp="(STL)[0-9]{4,4}",message="SHUTTLE_ID_INVALID")
	private String shuttleId;
	
	@Digits(integer = 7,fraction = 0, message = "REQUESTER_ID_INVALID")
	private Integer requester;
	
	@Length(min = 4, max=10, message="DC_FROM_LENGTH_VIOLATION")
	private String dcFrom;
	
	@Length(min = 4, max=10, message="DC_TO_LENGTH_VIOLATION")
	private String dcTo;
	
	@Length(min = 10, max=100, message="REASON_CHAR_LENGTH_VIOLATION")
	private String reason;
	
	@Pattern(regexp = "[0-9]{2,2}-[0-9]{2,2}-[0-9]{4,4}", message="INVALID_FOR_DATE")
	private String forDate;
	
	public FormShuttleRequest() { super(); }

	public String getShuttleId() {
		return shuttleId;
	}

	public Integer getRequester() {
		return requester;
	}

	public void setRequester(Integer requester) {
		this.requester = requester;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getForDate() {
		return forDate;
	}

	public void setForDate(String forDate) {
		this.forDate = forDate;
	}

	public String getDcFrom() {
		return dcFrom;
	}

	public void setDcFrom(String dcFrom) {
		this.dcFrom = dcFrom;
	}

	public String getDcTo() {
		return dcTo;
	}

	public void setDcTo(String dcTo) {
		this.dcTo = dcTo;
	}

	public void setShuttleId(String shuttleId) {
		this.shuttleId = shuttleId;
	}

	@Override
	public String toString() {
		return "FormShuttleRequest [shuttleId=" + shuttleId + ", requester=" + requester + ", dcFrom=" + dcFrom
				+ ", dcTo=" + dcTo + ", reason=" + reason + ", forDate=" + forDate + "]";
	}
	
}
