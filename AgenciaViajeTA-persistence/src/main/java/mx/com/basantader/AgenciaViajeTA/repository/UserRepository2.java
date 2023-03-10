package mx.com.basantader.AgenciaViajeTA.repository;

import mx.com.basantader.AgenciaViajeTA.model.UserEntity;
import mx.com.basantader.AgenciaViajeTA.model.UserEntity2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository2 extends JpaRepository<UserEntity2, Long> {

}
