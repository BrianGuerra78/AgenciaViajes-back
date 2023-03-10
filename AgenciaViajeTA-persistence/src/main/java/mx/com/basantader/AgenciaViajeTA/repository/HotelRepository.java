package mx.com.basantader.AgenciaViajeTA.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.com.basantader.AgenciaViajeTA.model.HotelEntity;

@Repository
public interface HotelRepository extends JpaRepository<HotelEntity, Long> {

	List<HotelEntity> searchByIdHotel(Long idHotel);

	List<HotelEntity> searchByNameHotel(String nameHotel);
	
	List<HotelEntity> searchByCodeHotel(String codeHotel);
	
	List<HotelEntity> searchByCityD(String city);
	
	Optional<HotelEntity> findByNameHotel(String nameHotel);
	
	Optional<HotelEntity> findByCodeHotel(String codeHotel);
	
}
