package mx.com.basantader.AgenciaViajeTA.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "PERMISSION_TA")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PermissionEntity implements Serializable {

    @Id
    @Column(name = "IDPERMISSION")
    @SequenceGenerator(name = "seque", sequenceName = "sequence_permission", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "seque")
    private Long idPermission;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @Column(name = "NODO", nullable = false)
    private String nodo;
}
