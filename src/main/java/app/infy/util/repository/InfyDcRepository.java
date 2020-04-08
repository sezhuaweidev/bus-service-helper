package app.infy.util.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.infy.util.entity.InfyDc;

@Repository
public interface InfyDcRepository extends JpaRepository<InfyDc, String> {

}
