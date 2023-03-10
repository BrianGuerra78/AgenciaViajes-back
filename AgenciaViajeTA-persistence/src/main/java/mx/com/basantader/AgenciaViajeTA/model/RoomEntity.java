package mx.com.basantader.AgenciaViajeTA.model;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "ROOMS_TA")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomEntity {

	@Column(name = "NAME", nullable = false)
	private String nameRoom;

	@Column(name = "DESCRIPTION", nullable = true)
	private String description;

	@Column(name = "NO_PEOPLE", nullable = false)
	private Long noPeople;

	@Column(name = "PRICE", nullable = false)
	private Double price;

	@Column(name = "STATUS", nullable = false)
	private Integer status;

	@Column(name = "CODE", nullable = false)
	private String codeRoom;

	@Id
	@SequenceGenerator(name = "ID_ROOM", sequenceName = "ROOM_SEQ", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "IDROOM", unique = true, nullable = false)
	private Long idRoom;

	@ManyToOne
	@JoinColumn(name = "FK_IDHOTEL")
	private HotelEntity idHotel;

	@OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private List<ReservationEntity> reservationroom;

}
