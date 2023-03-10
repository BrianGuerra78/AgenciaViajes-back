package mx.com.basantader.AgenciaViajeTA.service;

import mx.com.basantader.AgenciaViajeTA.dto.AnswerDTO;
import mx.com.basantader.AgenciaViajeTA.dto.RolePermDTO;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface RolePermService {

    RolePermDTO createRolePerm(@RequestBody RolePermDTO newRolePerm);

    RolePermDTO updateRolePerm(@RequestBody RolePermDTO updateRolePerm);

    RolePermDTO consultRolePerm(Long id);

    List<RolePermDTO> getRolePerm();

    AnswerDTO deleteRolePerm(Long id);
}
