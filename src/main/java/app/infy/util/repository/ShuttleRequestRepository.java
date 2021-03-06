package app.infy.util.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import app.infy.util.entity.ShuttleRequest;

@Repository
public interface ShuttleRequestRepository extends JpaRepository<ShuttleRequest, String> {
	
	//@Query("SELECT s FROM ShuttleRequest s WHERE s.approver=:approverId and (s.status='PENDING' or s.status='APPROVED_MGR'or s.status='REJECTED_MGR' or s.status='APPROVED_TRNS' or s.status='REJECTED_TRNS')") 
	@Query("SELECT s FROM ShuttleRequest s WHERE (s.approver=:approverId or s.requester=:requesterId) order by forDate DESC")
	public List<ShuttleRequest> findShuttleRequestByMngIdAndDate(@Param("approverId") Integer approverId,@Param("requesterId") Integer requesterId);

	 public List<ShuttleRequest> findByRequester(Integer id);
	 public List<ShuttleRequest> findByApprover(Integer id);
	 
	 //@Query("SELECT s FROM ShuttleRequest s WHERE s.dcFrom=:dcFrom and s.forDate = :forDate and s.status='APPROVED_MGR'")
	 @Query("SELECT s FROM ShuttleRequest s WHERE s.dcFrom=:dcFrom and (s.status='APPROVED_MGR' or s.status='APPROVED_TRNS' or s.status='REJECTED_TRNS') order by forDate DESC")
	 public List<ShuttleRequest> findShuttleRequestByTransMngIdAndDate(@Param("dcFrom")String dcId);
	 
	 //@Query("SELECT s FROM ShuttleRequest s WHERE s.requester=:requesterId and s.forDate = :forDate")
	 @Query("SELECT s FROM ShuttleRequest s WHERE s.requester=:requesterId order by forDate DESC")
	 public List<ShuttleRequest> findShuttleRequestByEmpMngIdAndDate(@Param("requesterId")Integer requesterId);

	 @Query("SELECT s FROM ShuttleRequest s WHERE s.shuttleId=:shuttleId and forDate like :forDate% and s.status='APPROVED_TRNS'")
     public List<ShuttleRequest> getSeatCountByReqTime(@Param("forDate")String currentDate, @Param("shuttleId")String reqTime);
	 
	 
	@Query("SELECT s FROM ShuttleRequest s WHERE forDate=:forDate and shuttleId=:shuttleReq and (status='PENDING' or s.status='APPROVED_MGR')")
	 public List<ShuttleRequest> findShuttleRequestBy(String forDate, String shuttleReq);
	 
}
