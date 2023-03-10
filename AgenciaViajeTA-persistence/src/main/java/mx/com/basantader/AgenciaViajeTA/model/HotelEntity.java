package mx.com.basantader.AgenciaViajeTA.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "HOTELS_TA")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotelEntity {

	@Column(name = "NAME", nullable = false)
	private String nameHotel;

	@Column(name = "ADDRESS", nullable = false)
	private String addressHotel;

	@Column(name = "LOGO", nullable = true)
	private byte[] logo;

	@Column(name = "STATUS", nullable = false)
	private Integer statusHotel;

	@Column(name = "CODE", nullable = false)
	private String codeHotel;

	@Id
	@Column(name = "IDHOTEL")
	@SequenceGenerator(name = "ID_HOTEL", sequenceName = "HOTEL_SEQ", initialValue = 1, allocationSize = 1)
	@GeneratedValue(generator = "ID_HOTEL")
	private Long idHotel;

	@OneToMany(mappedBy = "idHotel", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private List<RoomEntity> lstRoom;

	@ManyToOne
	@JoinColumn(name = "CITY_DESTINATION")
	private CityEntity cityD;
}
