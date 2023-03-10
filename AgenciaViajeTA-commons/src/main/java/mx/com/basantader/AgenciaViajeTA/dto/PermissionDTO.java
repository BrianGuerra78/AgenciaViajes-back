package mx.com.basantader.AgenciaViajeTA.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PermissionDTO {

    private Long idPermission;

    private String description;

    private String nodo;

}
