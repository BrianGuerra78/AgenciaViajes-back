package mx.com.basantader.AgenciaViajeTA.dto;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotelDTO implements Serializable {

	private Long idHotel;
	private String nameHotel;
	private Long city;
	private String addressHotel;
	private byte[] logo;
	private Integer statusHotel;
	private String codeHotel;

	private CityDTO cityDestination;

	private RoomDTO idRoom;

	private List<RoomDTO> lstRoom;

}
