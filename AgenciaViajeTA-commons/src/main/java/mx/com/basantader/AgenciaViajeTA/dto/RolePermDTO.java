package mx.com.basantader.AgenciaViajeTA.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RolePermDTO {

    private Long idRolePerm;

    private Long fk_idRole;

    private Long fk_idPermission;

    private RoleDTO roleDTO;

    private PermissionDTO permissionDTO;
}
