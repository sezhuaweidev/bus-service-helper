package app.infy.util.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.infy.util.entity.InfyRegion;

@Repository
public interface InfyRegionRepository extends JpaRepository<InfyRegion, String> {

}
