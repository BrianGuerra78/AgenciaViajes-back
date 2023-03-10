package mx.com.basantader.AgenciaViajeTA.repository;

import mx.com.basantader.AgenciaViajeTA.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;


import org.springframework.data.jpa.repository.Query;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import java.util.List;



@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query(value = "select * from USERS_TA where TOKEN like %:token%", nativeQuery = true)
    Optional<UserEntity> findByToken(String token);
}
