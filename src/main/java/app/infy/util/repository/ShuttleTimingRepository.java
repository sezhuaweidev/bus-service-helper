package app.infy.util.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.infy.util.entity.ShuttleTiming;

@Repository
public interface ShuttleTimingRepository extends JpaRepository<ShuttleTiming, String> {

}
