package app.infy.util.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import app.infy.util.entity.EmployeeDetail;

@Repository
public interface EmployeeDetailRepository extends JpaRepository<EmployeeDetail, Integer> {

	@Query("SELECT e FROM EmployeeDetail e WHERE e.empDc=:empDc and e.empType='TRANSPORT'")
	EmployeeDetail findByDc(@Param("empDc")String dcFrom);

}
