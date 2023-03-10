package mx.com.basantader.AgenciaViajeTA.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.com.basantader.AgenciaViajeTA.model.CityEntity;

public interface CityRepository extends JpaRepository<CityEntity, Long>  {

	List<CityEntity> findByNameOrContinentName(String name, String continentName );
	List<CityEntity> findByNameAndContinentName(String name, String continentName );
	
	Optional<CityEntity> findByName(String name);
	
}
