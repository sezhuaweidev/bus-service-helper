package app.infy.util.model;

public class ShuttleBookingStatus {
	private String requestId;
	private String shuttleStartTime;
	private String shuttleReturnTime1;
	private String shuttleReturnTime2;
	private String requesterName;
	private String approverName;
	private String reason;
	private String forDate;
	private String status;
	private String dcFrom;
	private String dcTo;
	
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

	public ShuttleBookingStatus() { super(); }

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getShuttleStartTime() {
		return shuttleStartTime;
	}

	public void setShuttleStartTime(String shuttleStartTime) {
		this.shuttleStartTime = shuttleStartTime;
	}

	public String getShuttleReturnTime1() {
		return shuttleReturnTime1;
	}

	public void setShuttleReturnTime1(String shuttleReturnTime1) {
		this.shuttleReturnTime1 = shuttleReturnTime1;
	}

	public String getShuttleReturnTime2() {
		return shuttleReturnTime2;
	}

	public void setShuttleReturnTime2(String shuttleReturnTime2) {
		this.shuttleReturnTime2 = shuttleReturnTime2;
	}

	public String getRequesterName() {
		return requesterName;
	}

	public void setRequesterName(String requesterName) {
		this.requesterName = requesterName;
	}

	public String getApproverName() {
		return approverName;
	}

	public void setApproverName(String approverName) {
		this.approverName = approverName;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
