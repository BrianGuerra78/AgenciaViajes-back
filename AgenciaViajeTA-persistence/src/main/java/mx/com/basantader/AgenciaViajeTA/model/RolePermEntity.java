package mx.com.basantader.AgenciaViajeTA.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "ROLEPERMISSION_TA")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RolePermEntity {
    @Id
    @Column(name = "IDROLEPERM")
    @SequenceGenerator(name = "SEQ_ROLEPERM", sequenceName = "SEQ_ROLEPERM", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "SEQ_ROLEPERM")
    private Long idRolePerm;

    @OneToOne
    @JoinColumn(name = "FK_IDROLE")
    private RoleEntity roleEntity;

    @OneToOne
    @JoinColumn(name = "FK_IDPERMISSION")
    private PermissionEntity permissionEntity;

}
