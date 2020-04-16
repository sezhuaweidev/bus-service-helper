package app.infy.util.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="employee_permission")
public class EmployeePermission {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="record_id")
	private String recordId;
	
	@Column(name="employee_id")
	private String employeeId;

	@Column(name="emp_permission_id")
	private String empPermissionId;
	
	public EmployeePermission() {
		super();
	}

	public String getRecordId() {
		return recordId;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmpPermissionId() {
		return empPermissionId;
	}

	public void setEmpPermissionId(String empPermissionId) {
		this.empPermissionId = empPermissionId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((empPermissionId == null) ? 0 : empPermissionId.hashCode());
		result = prime * result + ((employeeId == null) ? 0 : employeeId.hashCode());
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
		EmployeePermission other = (EmployeePermission) obj;
		if (empPermissionId == null) {
			if (other.empPermissionId != null)
				return false;
		} else if (!empPermissionId.equals(other.empPermissionId))
			return false;
		if (employeeId == null) {
			if (other.employeeId != null)
				return false;
		} else if (!employeeId.equals(other.employeeId))
			return false;
		return true;
	}
}
