package app.infy.util.entity;

public class EmployeeDetail {
	private Integer empId;

	private String empName;

	private String empMail;

	private Integer empManagerId;
	
	public EmployeeDetail() {
		super();
	}

	public Integer getEmpId() {
		return empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpMail() {
		return empMail;
	}

	public void setEmpMail(String empMail) {
		this.empMail = empMail;
	}

	public Integer getEmpManagerId() {
		return empManagerId;
	}

	public void setEmpManagerId(Integer empManagerId) {
		this.empManagerId = empManagerId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((empId == null) ? 0 : empId.hashCode());
		result = prime * result + ((empMail == null) ? 0 : empMail.hashCode());
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
		EmployeeDetail other = (EmployeeDetail) obj;
		if (empId == null) {
			if (other.empId != null)
				return false;
		} else if (!empId.equals(other.empId))
			return false;
		if (empMail == null) {
			if (other.empMail != null)
				return false;
		} else if (!empMail.equals(other.empMail))
			return false;
		return true;
	}
}