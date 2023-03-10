package mx.com.basantader.AgenciaViajeTA.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "RESERVATION_TA")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationEntity implements Serializable {
    @Id
    @Column(name = "IDRESERVATION")
    @SequenceGenerator(name = "sequ", sequenceName = "sequence_reservations", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "sequ")
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "LASTNAME_DAD", nullable = false)
    private String lastNameDad;

    @Column(name = "LASNAME_MON", nullable = false)
    private String lastNameMom;

    @Column(name = "DESCRIPTION" , nullable = false)
    private String description;

    @Column(name = "CREATE_DATE", nullable = false)
    private Date createDate;

    @Column(name = "START_DATE", nullable = false)
    private Date startDate;

    @Column(name = "END_DATE", nullable = false)
    private Date endDate;

    @ManyToOne
    @JoinColumn(name = "FK_IDROOM")
    private RoomEntity reservation;

    @ManyToOne
    @JoinColumn(name = "FK_IDUSER")
    private UserEntity reservationuser;

    @ManyToOne
    @JoinColumn(name = "FK_IDFLIGHTS")
    private FlightEntity reservationflight;



   /* @ManyToOne
    @JoinColumn(name = "FKIDFLIGHTS")
    private ComprasEntity compras;

    @ManyToOne
    @JoinColumn(name = "FK_ID_PRODUCTO")
    private ProductoEntity producto;

    */
}
