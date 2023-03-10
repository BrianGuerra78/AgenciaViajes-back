package mx.com.basantader.AgenciaViajeTA.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import mx.com.basantader.AgenciaViajeTA.model.FlightEntity;

public interface FlightRepository extends JpaRepository<FlightEntity, Long> {
	
	List<FlightEntity> findByCode(String code ); 
	
	
	
}

