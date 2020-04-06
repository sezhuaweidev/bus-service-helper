package app.infy.util.model;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

public class FormShuttleRequest {
	
	@Pattern(regexp="(STL)[0-9]{4,4}",message="SHUTTLE_ID_INVALID")
	private String shuttleId;
	@Digits(integer = 7,fraction = 0, message = "REQUESTER_ID_INVALID")
	private Integer requester;
	@Digits(integer = 7,fraction = 0, message = "APPROVER_ID_INVALID")
	private Integer approver;
	
	@Length(min = 10, max=100, message="REASON_CHAR_VIOLATION")
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

	public Integer getApprover() {
		return approver;
	}

	public void setApprover(Integer approver) {
		this.approver = approver;
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
}
