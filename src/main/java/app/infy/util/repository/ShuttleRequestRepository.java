package app.infy.util.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.infy.util.entity.ShuttleRequest;

@Repository
public interface ShuttleRequestRepository extends JpaRepository<ShuttleRequest, String> {

}
