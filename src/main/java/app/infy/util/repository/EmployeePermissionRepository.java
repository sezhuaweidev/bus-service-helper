package app.infy.util.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import app.infy.util.entity.EmployeePermission;

public interface EmployeePermissionRepository extends JpaRepository<EmployeePermission, Integer> {
	public List<EmployeePermission> findByEmployeeId(Integer empId);
}
