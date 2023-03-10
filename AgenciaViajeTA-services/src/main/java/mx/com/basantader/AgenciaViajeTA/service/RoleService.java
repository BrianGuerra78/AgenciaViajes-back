package mx.com.basantader.AgenciaViajeTA.service;

import mx.com.basantader.AgenciaViajeTA.dto.AnswerDTO;
import mx.com.basantader.AgenciaViajeTA.dto.PermissionDTO;
import mx.com.basantader.AgenciaViajeTA.dto.RoleDTO;
import mx.com.basantader.AgenciaViajeTA.dto.UserDTO;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface RoleService {

    RoleDTO createRole(@RequestBody RoleDTO newUser);

    RoleDTO updateRole(@RequestBody RoleDTO updateUser);

    RoleDTO consultRole(Long id);

    AnswerDTO deleteRole(Long id);

    List<RoleDTO> getRole();
}
