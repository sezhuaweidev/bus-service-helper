package app.infy.util.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="employee_detail")
public class EmployeeDetail {
	
	@Id
	@Column(name="emp_id")
	private Integer empId;

	@Column(name="emp_name")
	private String empName;

	@Column(name="emp_email")
	private String empMail;

	@Column(name="emp_manager_id")
	private Integer empManagerId;
	
	@Column(name="emp_type")
	private String empType;
	
	@Column(name="emp_dc")
	private String empDc;
	
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
	
	public String getEmpType() {
		return empType;
	}

	public void setEmpType(String empType) {
		this.empType = empType;
	}

	public String getEmpDc() {
		return empDc;
	}

	public void setEmpDc(String empDc) {
		this.empDc = empDc;
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
