package app.infy.util.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import app.infy.util.entity.ShuttleSeating;

@Repository
public interface ShuttleSeatingRepository extends JpaRepository<ShuttleSeating, String> {

	
	public List<ShuttleSeating> findByForDate(Date forDate);
	public ShuttleSeating findByCode(String code);
	
	@Query("select s.count from ShuttleSeating as s where s.code=:reqTime")
	Integer getSeatingStatusFor(@Param("reqTime")String reqTime);
	
}
