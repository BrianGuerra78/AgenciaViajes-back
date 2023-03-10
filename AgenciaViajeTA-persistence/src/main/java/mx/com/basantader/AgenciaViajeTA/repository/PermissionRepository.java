package mx.com.basantader.AgenciaViajeTA.repository;

import mx.com.basantader.AgenciaViajeTA.model.PermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PermissionRepository extends JpaRepository<PermissionEntity, Long> {

    @Query(value = "select * from PERMISSION_TA where DESCRIPTION like %:description%", nativeQuery = true)
    Optional<PermissionEntity> findByPermission(String description);
}
