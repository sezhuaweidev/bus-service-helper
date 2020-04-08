package app.infy.util.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="infy_dc")
public class InfyDc {

	@Id
	@Column(name="code")
	private String code;

	@Column(name="value")
	private String value;
	
	@Column(name = "country")
	private String country;
	
	@Column(name = "continent")
	private String continent;
	
	@Column(name = "travel_desk_mail")
	private String traveDeskMail;
	
	public String getContinent() {
		return continent;
	}

	public String getTraveDeskMail() {
		return traveDeskMail;
	}

	public void setTraveDeskMail(String traveDeskMail) {
		this.traveDeskMail = traveDeskMail;
	}

	public void setContinent(String continent) {
		this.continent = continent;
	}

	public InfyDc() { super(); }

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
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
		InfyDc other = (InfyDc) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}
}
