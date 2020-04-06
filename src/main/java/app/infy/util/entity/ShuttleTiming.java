package app.infy.util.entity;

import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="shuttle_timing")
public class ShuttleTiming {
	
	@Id
	@Column(name="code")
	private String code;

	@Column(name="start_time")
	private Time startTime;
	
	@Column(name="return_time1")
	private Time returnTime1;

	@Column(name="return_time2")
	private Time returnTime2;
	
	public ShuttleTiming() {
		super();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Time getStartTime() {
		return startTime;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	public Time getReturnTime1() {
		return returnTime1;
	}

	public void setReturnTime1(Time returnTime1) {
		this.returnTime1 = returnTime1;
	}

	public Time getReturnTime2() {
		return returnTime2;
	}

	public void setReturnTime2(Time returnTime2) {
		this.returnTime2 = returnTime2;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
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
		ShuttleTiming other = (ShuttleTiming) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ShuttleTiming [code=" + code + ", startTime=" + startTime + ", returnTime1=" + returnTime1
				+ ", returnTime2=" + returnTime2 + "]";
	}
	
}
