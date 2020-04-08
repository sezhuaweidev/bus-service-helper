package app.infy.util.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.infy.util.entity.InfyCountry;

@Repository
public interface InfyCountryRepository extends JpaRepository<InfyCountry, String>{
	public List<InfyCountry> findByContinent(String continentName);
}
