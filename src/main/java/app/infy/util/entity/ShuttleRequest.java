package app.infy.util.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="shuttle_request")
public class ShuttleRequest {
	
	@Id
	@Column(name="req_id",updatable = true)
	private String requestId;
	
	@Column(name="shuttle_id")
	private String shuttleId;
	
	@Column(name="requester")
	private Integer requester;
	
	@Column(name="approver")
	private Integer approver;
	
	@Column(name="dc_from")
	private String dcFrom;
	
	@Column(name="dc_to")
	private String dcTo;
	
	@Column(name="reason")
	private String reason;
	
	@Column(name="remark")
	private String remark;
	
	@Column(name="mng_remark")
	private String mngRemark;
	
	@Column(name="trns_remark")
	private String trnsRemark;

	@Column(name="for_date")
	private String forDate;
	
	@Column(name="created_at")
	private Timestamp createdAt;
	
	private String status;
	
	public ShuttleRequest() {
		super();
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
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
	
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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
	
	public String getMngRemark() {
		return mngRemark;
	}

	public void setMngRemark(String mngRemark) {
		this.mngRemark = mngRemark;
	}
	
	public String getTrnsRemark() {
		return trnsRemark;
	}

	public void setTrnsRemark(String trnsRemark) {
		this.trnsRemark = trnsRemark;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((approver == null) ? 0 : approver.hashCode());
		result = prime * result + ((forDate == null) ? 0 : forDate.hashCode());
		result = prime * result + ((requester == null) ? 0 : requester.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ShuttleRequest other = (ShuttleRequest) obj;
		if (approver == null) {
			if (other.approver != null)
				return false;
		} else if (!approver.equals(other.approver))
			return false;
		if (forDate == null) {
			if (other.forDate != null)
				return false;
		} else if (!forDate.equals(other.forDate))
			return false;
		if (requester == null) {
			if (other.requester != null)
				return false;
		} else if (!requester.equals(other.requester))
			return false;
		return true;
	}

	public String getShuttleId() {
		return shuttleId;
	}

	public void setShuttleId(String shuttleId) {
		this.shuttleId = shuttleId;
	}
}
