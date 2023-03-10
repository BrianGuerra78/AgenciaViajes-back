package mx.com.basantader.AgenciaViajeTA.repository;

import mx.com.basantader.AgenciaViajeTA.model.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuartoRepository extends JpaRepository<RoomEntity, Long> {
}
