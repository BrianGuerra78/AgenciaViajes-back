package mx.com.basantader.AgenciaViajeTA.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long idUser;

    private String name;

    private String lastnameDad;

    private String lastnameMom;

    private Long fk_idRole;

    private String token;

    private RoleDTO rolDto;
}
