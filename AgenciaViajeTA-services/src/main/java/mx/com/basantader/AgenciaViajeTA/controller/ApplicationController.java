package mx.com.basantader.AgenciaViajeTA.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import mx.com.basantader.AgenciaViajeTA.exceptions.BusinessException;
import mx.com.basantader.AgenciaViajeTA.model.UserEntity;
import mx.com.basantader.AgenciaViajeTA.model.UserEntity2;
import mx.com.basantader.AgenciaViajeTA.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import mx.com.basantader.AgenciaViajeTA.dto.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import mx.com.basantader.AgenciaViajeTA.model.ApplicationItem;

import mx.com.basantader.AgenciaViajeTA.commons.Messages;
import mx.com.basantader.AgenciaViajeTA.exceptions.ResourceNotFoundException;

/**
 * Created by
 */
@RestController
@RequestMapping("/API")
@Api(value = "Applciation AgenciaViajeTA")
public class ApplicationController {

    //private static final Logger log = LoggerFactory.getLogger(ApplicationController.class);

    //Usuario
    @Autowired
    private UserService userService;

    //Role
    @Autowired
    private RoleService roleService;

    //Permisos
    @Autowired
    private PermissionService permissionService;

    //Role-Permission
    @Autowired
    private RolePermService rolePermService;

    @Autowired
    private ApplicationService applicationService;

    /*@RequestMapping(method = RequestMethod.GET, produces = "application/json"
            , value = {"/getAllAlerts"})
    @ResponseBody
    @ApiOperation(value = "view the list of ALL current active created stored appllication items", response = ApplicationEntry.class)
    public List<ApplicationEntry> getAllAlerts() {
        log.debug("Trying to retrieve all alerts");
        return applicationService.getApplicationItems();

    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json"
            , value = {"/getAll"})
    @ResponseBody
    @ApiOperation(value = "view the list of ALL current active created stored appllication items. this methos throws not found exception"
            , response = ApplicationEntry.class)
    public List<ApplicationEntry> getAll() {
        log.debug("Trying to retrieve all alerts");
        throw new ResourceNotFoundException(Messages.getMessage(1));
    }


    @RequestMapping(method = RequestMethod.POST, produces = "application/json"
            , value = {"/createAlert"})
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create an application entry into the application manager")
    public void createAlert(@Valid @RequestBody ApplicationEntry request) {
        log.debug("Trying to create an alert: {}", request.toString());
        applicationService.createApplicationItem(request);
    }*/

    @RequestMapping(method = {RequestMethod.GET}, value = {"/version"})
    public String getVersion() {
        return "1.0";
    }

    //Usuario
    @PostMapping(value = "/createUser")
    public UserDTO createUser(@RequestBody UserDTO newUser) {
        if (newUser.getName() == null) {
            throw new BusinessException(6);
        }
        return userService.createUser(newUser);
    }

    @PostMapping(value = "/updateUser/{id}")
    public UserDTO updateUser(@PathVariable Long id, @RequestBody UserDTO updateUser) {
        updateUser.setIdUser(id);
        if (updateUser.getIdUser() == null) {
            throw new BusinessException();
        }
        return userService.updateUser(updateUser);
    }
    @GetMapping(value = "/consultUser/{id}")
    public UserDTO consultUser(@PathVariable Long id){
        return userService.consultUser(id);
    }

    @DeleteMapping(value = "/deleteUser/{id}")
    public AnswerDTO deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }

    /*@GetMapping(value="/searchUsers")
    public List<UserDTO> searchUsers(@RequestParam(required=false) Long idRole){
    	return userService.searchUsers(idRole);
    }*/

    @GetMapping (value="/getUsers")
    public List<UserDTO> getUsers(){
    	return userService.getUsers();
    }

    //paginacion
    @GetMapping (value="/getUsersPage")
    public ResponseEntity<Page<UserEntity2>> paginas(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String order,
            @RequestParam(defaultValue = "true") boolean asc
    ){
        Page<UserEntity2> usuarios = userService.paginas(
                PageRequest.of(page, size, Sort.by(order)));
        if(!asc)
            usuarios = userService.paginas(
                    PageRequest.of(page, size, Sort.by(order).descending()));
        return new ResponseEntity<Page<UserEntity2>>(usuarios, HttpStatus.OK);
    }


    //Role
    @PostMapping(value = "/createRole")
    public RoleDTO createRole(@RequestBody RoleDTO newRole) {
        if (newRole.getTipoRole() == null) {
            throw new BusinessException(5);
        }
        return roleService.createRole(newRole);
    }

    @PostMapping(value = "/updateRole/{id}")
    public RoleDTO updateRole(@PathVariable Long id, @RequestBody RoleDTO updateRole) {
        updateRole.setIdRole(id);
        if (updateRole.getIdRole() == null) {
            throw new BusinessException(5);
        }
        return roleService.updateRole(updateRole);
    }

    @GetMapping(value = "/consultRole/{id}")
    public RoleDTO consultRole(@PathVariable Long id){
        return roleService.consultRole(id);
    }

    @DeleteMapping(value = "/deleteRole/{id}")
    public AnswerDTO deleteRole(@PathVariable Long id) {
        return roleService.deleteRole(id);
    }

    @GetMapping (value="/getRole")
    public List<RoleDTO> getRole(){
        return roleService.getRole();
    }

    //Permission
    @PostMapping(value = "/createPermission")
    public PermissionDTO createPermission(@RequestBody PermissionDTO newPermission) {
        if (newPermission.getDescription() == null) {
            throw new BusinessException(5);
        }
        return permissionService.createPermission(newPermission);
    }

    @PostMapping(value = "/updatePermission/{id}")
    public PermissionDTO updatePermission(@PathVariable Long id, @RequestBody PermissionDTO updatePermission) {
        updatePermission.setIdPermission(id);
        if (updatePermission.getIdPermission() == null) {
            throw new BusinessException(8);
        }
        return permissionService.updatePermission(updatePermission);
    }

    @GetMapping(value = "/consultPermission/{id}")
    public PermissionDTO consultPermission(@PathVariable Long id) {
        return permissionService.consultPermission(id);
    }
    @DeleteMapping(value = "/deletePermission/{id}")
    public AnswerDTO deletePermission(@PathVariable Long id) {
        return permissionService.deletePermission(id);
    }


    @GetMapping (value="/getPermission")
    public List<PermissionDTO> getPermission(){
        return permissionService.getPermission();
    }

    //Role-Permission
    @PostMapping(value = "/createRolePerm")
    public RolePermDTO createRolePerm(@RequestBody RolePermDTO newRolePerm) {
        
        return rolePermService.createRolePerm(newRolePerm);
    }

    @PostMapping(value = "/updateRolePerm/{id}")
    public RolePermDTO updateRolePerm(@PathVariable Long id, @RequestBody RolePermDTO updateRolePerm) {
        updateRolePerm.setIdRolePerm(id);
        if (updateRolePerm.getIdRolePerm() == null) {
            throw new BusinessException(8);
        }
        return rolePermService.updateRolePerm(updateRolePerm);
    }

    @DeleteMapping(value = "/deleteRolePerm/{id}")
    public AnswerDTO deleteRolePerm(@PathVariable Long id) {
        return rolePermService.deleteRolePerm(id);
    }

    @GetMapping(value = "/consultRolePerm/{id}")
    public RolePermDTO consultRolePerm(@PathVariable Long id) {
        return rolePermService.consultRolePerm(id);
    }

    @GetMapping (value="/getRolePerm")
    public List<RolePermDTO> getRolePerm(){
        return rolePermService.getRolePerm();
    }

}
