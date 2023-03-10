package mx.com.basantader.AgenciaViajeTA.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.com.basantader.AgenciaViajeTA.model.RoomEntity;

@Repository
public interface RoomRepository extends JpaRepository<RoomEntity, Long> {

	List<RoomEntity> searchByIdRoom(Long idRoom);

	List<RoomEntity> searchByNameRoom(String nameRoom);
	
	List<RoomEntity> searchByCodeRoom(String codeRoom);
	
	List<RoomEntity> searchByIdHotel(Long idHotel);

	Optional<RoomEntity> findByNameRoom(String nameRoom);
	
	Optional<RoomEntity> findByCodeRoom(String codeRoom);
	
	Optional<RoomEntity> findByIdHotel(Long idHotel);
	
}
