package mx.com.basantader.AgenciaViajeTA.service.impl;

import mx.com.basantader.AgenciaViajeTA.dto.AnswerDTO;
import mx.com.basantader.AgenciaViajeTA.dto.PermissionDTO;
import mx.com.basantader.AgenciaViajeTA.dto.RoleDTO;
import mx.com.basantader.AgenciaViajeTA.dto.UserDTO;
import mx.com.basantader.AgenciaViajeTA.exceptions.BusinessException;
import mx.com.basantader.AgenciaViajeTA.model.PermissionEntity;
import mx.com.basantader.AgenciaViajeTA.model.RoleEntity;
import mx.com.basantader.AgenciaViajeTA.model.UserEntity;
import mx.com.basantader.AgenciaViajeTA.repository.PermissionRepository;
import mx.com.basantader.AgenciaViajeTA.service.PermissionService;
import mx.com.basantader.AgenciaViajeTA.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PermissionImpl implements PermissionService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    PermissionRepository permissionRepository;

    @Override
    public PermissionDTO createPermission(PermissionDTO newPermission) {
        Optional<PermissionEntity> listPermission = permissionRepository.findByPermission(newPermission.getDescription());
        if (listPermission.isPresent()) {
            throw new BusinessException(10);
        }
        PermissionEntity newRegistration = new PermissionEntity();
        newRegistration.setIdPermission(newPermission.getIdPermission());
        newRegistration.setDescription(newPermission.getDescription());
        newRegistration.setNodo(newPermission.getNodo());
        permissionRepository.save(newRegistration);
        newPermission.setIdPermission(newRegistration.getIdPermission());
        return newPermission;
    }

    @Override
    @Transactional
    public PermissionDTO updatePermission(PermissionDTO updatePermission) {
        Optional<PermissionEntity> update = permissionRepository.findById(updatePermission.getIdPermission());
        if (!update.isPresent()) {
            throw new BusinessException(12);
        }
        PermissionEntity updateRole2 = update.get();
        updateRole2.setDescription(updatePermission.getDescription());
        updateRole2.setNodo(updatePermission.getNodo());
        permissionRepository.save(updateRole2);
        updatePermission.setIdPermission(updateRole2.getIdPermission());
        return updatePermission;
    }

    @Override
    @Transactional
    public PermissionDTO consultPermission(Long id) {
        PermissionDTO retorno = new PermissionDTO();
        Optional<PermissionEntity> registPerm = permissionRepository.findById(id);
        if (!registPerm.isPresent()) {
            throw new BusinessException(8);
        }
        retorno.setIdPermission(registPerm.get().getIdPermission());
        retorno.setDescription(registPerm.get().getDescription());
        retorno.setNodo(registPerm.get().getNodo());
        return retorno;
    }

    @Override
    @Transactional
    public AnswerDTO deletePermission(Long id) {
        AnswerDTO answer = new AnswerDTO();
        Optional<PermissionEntity> perRegister = permissionRepository.findById(id);
        if (!perRegister.isPresent()) {
            throw new BusinessException(8);
        }
        permissionRepository.deleteById(id);
        answer.setMessageAnswer("Registro eliminado correctamente");
        return answer;
    }

    @Override
    public List<PermissionDTO> getPermission() {
        List<PermissionEntity> lstPermission = permissionRepository.findAll(Sort.by("idPermission"));
        List<PermissionDTO> lstReturn = new ArrayList<PermissionDTO>();
        PermissionDTO aux;
        for(PermissionEntity permission: lstPermission) {
            aux = new PermissionDTO();
            aux.setIdPermission(permission.getIdPermission());
            aux.setDescription(permission.getDescription());
            aux.setNodo(permission.getNodo());
            lstReturn.add(aux);
        }
        return lstReturn;
    }
}
