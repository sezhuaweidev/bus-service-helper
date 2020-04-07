package app.infy.util.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.infy.util.entity.InfyDc;

public interface InfyDcRepository extends JpaRepository<InfyDc, String> {

}
