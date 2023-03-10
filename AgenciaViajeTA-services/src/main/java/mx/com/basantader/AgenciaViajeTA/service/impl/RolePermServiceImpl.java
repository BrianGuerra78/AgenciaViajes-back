package mx.com.basantader.AgenciaViajeTA.service.impl;

import mx.com.basantader.AgenciaViajeTA.dto.*;
import mx.com.basantader.AgenciaViajeTA.exceptions.BusinessException;
import mx.com.basantader.AgenciaViajeTA.model.PermissionEntity;
import mx.com.basantader.AgenciaViajeTA.model.RoleEntity;
import mx.com.basantader.AgenciaViajeTA.model.RolePermEntity;
import mx.com.basantader.AgenciaViajeTA.model.UserEntity;
import mx.com.basantader.AgenciaViajeTA.repository.PermissionRepository;
import mx.com.basantader.AgenciaViajeTA.repository.RolePermRepository;
import mx.com.basantader.AgenciaViajeTA.repository.RoleRepository;
import mx.com.basantader.AgenciaViajeTA.service.RolePermService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RolePermServiceImpl implements RolePermService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    RolePermRepository rolePermRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PermissionRepository permissionRepository;
    
    @Override
    public RolePermDTO createRolePerm(RolePermDTO newRolePerm){
        RolePermEntity newRegistration = new RolePermEntity();
        Optional<RoleEntity> roles = roleRepository.findById(newRolePerm.getRoleDTO().getIdRole());
        RoleEntity nuevoRol = roles.get();
        newRegistration.setRoleEntity(nuevoRol);
        Optional<PermissionEntity> permisos = permissionRepository.findById((newRolePerm.getPermissionDTO().getIdPermission()));
        PermissionEntity nuevoPermiso = permisos.get();
        newRegistration.setPermissionEntity(nuevoPermiso);
        rolePermRepository.save(newRegistration);
        newRolePerm.setIdRolePerm(newRegistration.getIdRolePerm());
        return newRolePerm;
    }

    @Override
    public RolePermDTO updateRolePerm(RolePermDTO updateRolePerm) {
        Optional<RolePermEntity> update = rolePermRepository.findById(updateRolePerm.getIdRolePerm());
        Optional<RoleEntity> roleID =roleRepository.findById(updateRolePerm.getFk_idRole());
        Optional<PermissionEntity> permissionID =permissionRepository.findById(updateRolePerm.getFk_idPermission());
        if (!update.isPresent()) {
            throw new BusinessException(8);
        }
        RolePermEntity updateRolePerm2 = update.get();
        updateRolePerm2.setRoleEntity(roleID.get());
        updateRolePerm2.setPermissionEntity(permissionID.get());
        rolePermRepository.save(updateRolePerm2);
        updateRolePerm.setIdRolePerm(update.get().getIdRolePerm());
        return updateRolePerm;
    }

    @Override
    public RolePermDTO consultRolePerm(Long id) {
        RolePermDTO retorno = new RolePermDTO();
        Optional<RolePermEntity> registRolPerm = rolePermRepository.findById(id);
        if (!registRolPerm.isPresent()) {
            throw new BusinessException(8);
        }
        retorno.setIdRolePerm(registRolPerm.get().getIdRolePerm());
        retorno.setFk_idRole(registRolPerm.get().getRoleEntity().getIdRole());
        retorno.setFk_idPermission(registRolPerm.get().getPermissionEntity().getIdPermission());
        Optional<RoleEntity> roles =roleRepository.findById(registRolPerm.get().getRoleEntity().getIdRole());
        RoleDTO role = new RoleDTO();
        role.setIdRole(roles.get().getIdRole());
        role.setTipoRole(roles.get().getTiporole());
        retorno.setRoleDTO(role);
        Optional<PermissionEntity> permisos =permissionRepository.findById(registRolPerm.get().getPermissionEntity().getIdPermission());
        PermissionDTO permiso = new PermissionDTO();
        permiso.setIdPermission(permisos.get().getIdPermission());
        permiso.setDescription(permisos.get().getDescription());
        permiso.setNodo(permisos.get().getNodo());
        retorno.setPermissionDTO(permiso);

        return retorno;
    }

    @Override
    public AnswerDTO deleteRolePerm(Long id) {
        AnswerDTO answer = new AnswerDTO();
        Optional<RolePermEntity> rolePerRegister = rolePermRepository.findById(id);
        if (!rolePerRegister.isPresent()) {
            throw new BusinessException(8);
        }
        rolePermRepository.deleteById(id);
        answer.setMessageAnswer("Registro eliminado correctamente");
        return answer;
    }

    @Override
    public List<RolePermDTO> getRolePerm() {
        List<RolePermEntity> lstUsers = rolePermRepository.findAll(Sort.by("idRolePerm"));
        List<RolePermDTO> lstReturn = new ArrayList<RolePermDTO>();
        RolePermDTO aux;
        for(RolePermEntity rolePerm: lstUsers) {
            aux = new RolePermDTO();
            aux.setIdRolePerm(rolePerm.getIdRolePerm());
            aux.setFk_idRole(rolePerm.getRoleEntity().getIdRole());
            aux.setFk_idPermission(rolePerm.getPermissionEntity().getIdPermission());

            Optional<RoleEntity> roles =roleRepository.findById(rolePerm.getRoleEntity().getIdRole());
            RoleDTO role = new RoleDTO();
            role.setIdRole(roles.get().getIdRole());
            role.setTipoRole(roles.get().getTiporole());
            aux.setRoleDTO(role);

            Optional<PermissionEntity> permisos =permissionRepository.findById(rolePerm.getPermissionEntity().getIdPermission());
            PermissionDTO permiso = new PermissionDTO();
            permiso.setIdPermission(permisos.get().getIdPermission());
            permiso.setDescription(permisos.get().getDescription());
            permiso.setNodo(permisos.get().getNodo());
            aux.setPermissionDTO(permiso);

            lstReturn.add(aux);
        }
        return lstReturn;
    }
}
