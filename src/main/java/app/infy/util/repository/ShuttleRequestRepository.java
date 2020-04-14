package app.infy.util.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import app.infy.util.entity.ShuttleRequest;

@Repository
public interface ShuttleRequestRepository extends JpaRepository<ShuttleRequest, String> {
	
	 @Query("SELECT s FROM ShuttleRequest s WHERE s.approver=:approverId and s.forDate = :forDate and s.status='PENDING'")
	 public List<ShuttleRequest> findShuttleRequestByMngIdAndDate(@Param("approverId") Integer approverId,@Param("forDate") String forDate);

	 public List<ShuttleRequest> findByRequester(Integer id);
	 public List<ShuttleRequest> findByApprover(Integer id);
	 @Query("SELECT s FROM ShuttleRequest s WHERE s.dcFrom=:dcFrom and s.forDate = :forDate and s.status='APPROVED_MGR'")
	 public List<ShuttleRequest> findShuttleRequestByTransMngIdAndDate(@Param("dcFrom")String dcId, @Param("forDate")String curDate);
	 @Query("SELECT s FROM ShuttleRequest s WHERE s.requester=:requesterId and s.forDate = :forDate")
	 public List<ShuttleRequest> findShuttleRequestByEmpMngIdAndDate(@Param("requesterId")Integer requesterId, @Param("forDate")String curDate);
	 
}
