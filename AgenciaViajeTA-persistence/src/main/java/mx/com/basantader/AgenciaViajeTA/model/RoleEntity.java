package mx.com.basantader.AgenciaViajeTA.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ROLE_TA")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleEntity implements Serializable {

    @Id
    @Column(name = "IDROLE")
    @SequenceGenerator(name = "seque", sequenceName = "sequence_role", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "seque")
    private Long idRole;

    @Column(name = "TIPOROLE", nullable = false)
    private String tiporole;
}
