package mx.com.basantader.AgenciaViajeTA.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "USERS_TA")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity implements Serializable {

    @Id
    @Column(name = "IDUSER")
    @SequenceGenerator(name = "sequ", sequenceName = "sequence_user", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "sequ")
    private Long idUser;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "LASTNAME_DAD", nullable = false)
    private String lastnameDad;

    @Column(name = "LASTNAME_MOM", nullable = false)
    private String lastnameMom;

    @OneToOne
    @JoinColumn(name = "FK_IDROLE")
    private RoleEntity roleItem;

    @Column(name = "TOKEN", nullable = false)
    private String token;


    @OneToMany(mappedBy="reservationuser",cascade = CascadeType.ALL, fetch = FetchType.LAZY,  orphanRemoval = true)
    private List<ReservationEntity> reservationuser;

}
