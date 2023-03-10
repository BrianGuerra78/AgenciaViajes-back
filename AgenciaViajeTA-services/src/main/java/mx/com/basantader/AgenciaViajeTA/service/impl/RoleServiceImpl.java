package mx.com.basantader.AgenciaViajeTA.service.impl;

import mx.com.basantader.AgenciaViajeTA.dto.AnswerDTO;
import mx.com.basantader.AgenciaViajeTA.dto.RoleDTO;
import mx.com.basantader.AgenciaViajeTA.dto.UserDTO;
import mx.com.basantader.AgenciaViajeTA.exceptions.BusinessException;
import mx.com.basantader.AgenciaViajeTA.model.RoleEntity;
import mx.com.basantader.AgenciaViajeTA.model.RolePermEntity;
import mx.com.basantader.AgenciaViajeTA.model.UserEntity;
import mx.com.basantader.AgenciaViajeTA.repository.RolePermRepository;
import mx.com.basantader.AgenciaViajeTA.repository.RoleRepository;
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
public class RoleServiceImpl implements RoleService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    RolePermRepository rolePermRepository;

    @Override
    public RoleDTO createRole(RoleDTO newUser) {
        Optional<RoleEntity> listRole = roleRepository.findByRole(newUser.getTipoRole());
        if (listRole.isPresent()){
            throw new BusinessException(7);
        }
        RoleEntity newRegistration = new RoleEntity();
        newRegistration.setIdRole(newUser.getIdRole());
        newRegistration.setTiporole(newUser.getTipoRole());
        roleRepository.save(newRegistration);
        newUser.setIdRole(newRegistration.getIdRole());
        return newUser;
    }

    @Override
    @Transactional
    public RoleDTO updateRole(RoleDTO updateRole) {
        Optional<RoleEntity> update = roleRepository.findById(updateRole.getIdRole());
        if (!update.isPresent()) {
            throw new BusinessException(10);
        }
        RoleEntity updateRole2 = update.get();
        updateRole2.setTiporole(updateRole.getTipoRole());
        roleRepository.save(updateRole2);
        updateRole.setIdRole(updateRole2.getIdRole());
        return updateRole;
    }

    @Override
    @Transactional
    public RoleDTO consultRole(Long id) {
        RoleDTO retorno = new RoleDTO();
        Optional<RoleEntity> registRol = roleRepository.findById(id);
        if (!registRol.isPresent()) {
            throw new BusinessException(8);
        }
        retorno.setIdRole(registRol.get().getIdRole());
        retorno.setTipoRole(registRol.get().getTiporole());
        return retorno;
    }

    @Override
    @Transactional
    public AnswerDTO deleteRole(Long id) {
        AnswerDTO answer = new AnswerDTO();
        Optional<RoleEntity> roleRegister = roleRepository.findById(id);
        if (!roleRegister.isPresent()) {
            throw new BusinessException(8);
        }
        roleRepository.deleteById(id);
        answer.setMessageAnswer("Registro eliminado correctamente");
        return answer;
    }
    @Override
    public List<RoleDTO> getRole() {
        List<RoleEntity> lstRole = roleRepository.findAll(Sort.by("idRole"));
        List<RoleDTO> lstReturn = new ArrayList<RoleDTO>();
        RoleDTO aux;
        for(RoleEntity roles: lstRole) {
            aux = new RoleDTO();
            aux.setIdRole(roles.getIdRole());
            aux.setTipoRole(roles.getTiporole());
            lstReturn.add(aux);
        }
        return lstReturn;
    }
}
