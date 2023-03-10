package mx.com.basantader.AgenciaViajeTA.service.impl;

import mx.com.basantader.AgenciaViajeTA.dto.*;
import mx.com.basantader.AgenciaViajeTA.exceptions.BusinessException;
import mx.com.basantader.AgenciaViajeTA.model.RoleEntity;
import mx.com.basantader.AgenciaViajeTA.model.UserEntity;
import mx.com.basantader.AgenciaViajeTA.model.UserEntity2;
import mx.com.basantader.AgenciaViajeTA.repository.RoleRepository;
import mx.com.basantader.AgenciaViajeTA.repository.UserRepository;
import mx.com.basantader.AgenciaViajeTA.repository.UserRepository2;
import mx.com.basantader.AgenciaViajeTA.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserRepository2 userRepository2;

    @Autowired
    RoleRepository roleRepository;

    @Override
    @Transactional
    public UserDTO createUser(UserDTO newUser) {
        Optional<UserEntity> register = userRepository.findByToken(newUser.getToken());
        if (register.isPresent()) {
            throw new BusinessException(14);
        }
        UserEntity newRegistration = new UserEntity();
        newRegistration.setName(newUser.getName());
        newRegistration.setLastnameDad(newUser.getLastnameDad());
        newRegistration.setLastnameMom(newUser.getLastnameMom());
        Optional<RoleEntity> rol =roleRepository.findById(newUser.getRolDto().getIdRole());
        RoleEntity nuevaEntidad = rol.get();
        //RoleDTO nuevoRole = newUser.getRolDto();
        newRegistration.setRoleItem(nuevaEntidad);
        newRegistration.setToken(newUser.getToken());
        userRepository.save(newRegistration);
        newUser.setIdUser(newRegistration.getIdUser());
        return newUser;
    }

    @Override
    @Transactional
    public UserDTO updateUser(UserDTO updateUser) {
        Optional<UserEntity> update = userRepository.findById(updateUser.getIdUser());
        if (!update.isPresent()) {
            throw new BusinessException(8);
        }
        UserEntity updateUser2 = update.get();
        updateUser2.setName(updateUser.getName());
        updateUser2.setLastnameDad(updateUser.getLastnameDad());
        updateUser2.setLastnameMom(updateUser.getLastnameMom());
        Optional<RoleEntity> rol =roleRepository.findById(updateUser.getRolDto().getIdRole());
        RoleEntity nuevaEntidad = rol.get();
        //RoleDTO nuevoRole = newUser.getRolDto();
        updateUser2.setRoleItem(nuevaEntidad);
        updateUser2.setToken(updateUser.getToken());
        userRepository.save(updateUser2);
        updateUser.setIdUser(updateUser2.getIdUser());
        return updateUser;
    }

    @Override
    @Transactional
    public UserDTO consultUser(Long id) {
        UserDTO retorno = new UserDTO();
        Optional<UserEntity> registUser = userRepository.findById(id);
        if (!registUser.isPresent()) {
            throw new BusinessException(8);
        }
        retorno.setIdUser(registUser.get().getIdUser());
        retorno.setName(registUser.get().getName());
        retorno.setLastnameDad(registUser.get().getLastnameDad());
        retorno.setLastnameMom(registUser.get().getLastnameMom());
        retorno.setFk_idRole(registUser.get().getRoleItem().getIdRole());
        retorno.setToken(registUser.get().getToken());
        return retorno;
    }

    @Override
    @Transactional
    public AnswerDTO deleteUser(Long id) {
        AnswerDTO answer = new AnswerDTO();
        Optional<UserEntity> userRegister = userRepository.findById(id);
        if (!userRegister.isPresent()) {
            throw new BusinessException(8);
        }
        userRepository.deleteById(id);
        answer.setMessageAnswer("Registro eliminado correctamente");
        return answer;
    }

    @Override
		public List<UserDTO> getUsers() {
			List<UserEntity> lstUsers = userRepository.findAll(Sort.by("idUser"));
			List<UserDTO> lstReturn = new ArrayList<UserDTO>();
			UserDTO aux;
			for(UserEntity users: lstUsers) {
				aux = new UserDTO();
				aux.setIdUser(users.getIdUser());
				aux.setName(users.getName());
				aux.setLastnameDad(users.getLastnameDad());
				aux.setLastnameMom(users.getLastnameMom());
                aux.setFk_idRole(users.getRoleItem().getIdRole());
                Optional<RoleEntity> roles =roleRepository.findById(users.getRoleItem().getIdRole());
                RoleDTO role = new RoleDTO();
                role.setIdRole(roles.get().getIdRole());
                role.setTipoRole(roles.get().getTiporole());
				aux.setRolDto(role);
				aux.setToken(users.getToken());
				lstReturn.add(aux);
				}
			   return lstReturn;
		}

        //get para paginacion
    public Page<UserEntity2> paginas(Pageable pageable){
        return userRepository2.findAll(pageable);
    }
}
