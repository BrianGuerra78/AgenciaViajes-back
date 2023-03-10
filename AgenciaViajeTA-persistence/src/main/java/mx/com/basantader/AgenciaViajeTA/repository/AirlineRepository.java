package mx.com.basantader.AgenciaViajeTA.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import mx.com.basantader.AgenciaViajeTA.model.AirlineEntity;

public interface AirlineRepository extends JpaRepository<AirlineEntity, Long> {
	
	List<AirlineEntity> findByName(String name);
	
	
	


	


}
