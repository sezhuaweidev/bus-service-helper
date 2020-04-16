package app.infy.util.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import app.infy.util.entity.EmployeeAuth;

public interface EmployeeAuthRepository extends JpaRepository<EmployeeAuth, Integer> {
	
	@Query(value = "from EmployeeAuth where username=?1")
	public Optional<EmployeeAuth> getUsernamePassword(String username);

	@Query(value = "from EmployeeAuth where id=?1")
	public Optional<EmployeeAuth> getIdPassword(Integer id);
	
}
